package org.codepanda.userinterface.test;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.SwingUtilities;

import org.codepanda.userinterface.ContactInfoPanel;
import org.codepanda.userinterface.PhoneMeFrame;
import org.codepanda.utility.contact.ContactOperations;
import org.codepanda.utility.data.DataPool;
import org.codepanda.utility.label.RelationLabel;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.layout.Layout;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.activity.Activity;
import prefuse.controls.ControlAdapter;
import prefuse.controls.PanControl;
import prefuse.controls.ZoomControl;
import prefuse.data.Graph;
import prefuse.data.Schema;
import prefuse.data.Table;
import prefuse.render.AbstractShapeRenderer;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.LabelRenderer;
import prefuse.render.PolygonRenderer;
import prefuse.render.Renderer;
import prefuse.util.ColorLib;
import prefuse.util.GraphicsLib;
import prefuse.visual.AggregateItem;
import prefuse.visual.AggregateTable;
import prefuse.visual.VisualGraph;
import prefuse.visual.VisualItem;

/**
 * @author hszcg
 * 
 *         Created by prefuse, edit by hszcg
 * 
 */
public class RelationNetShow extends Display {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8558860413211768565L;
	public static final String GRAPH = "graph";
	public static final String NODES = "graph.nodes";
	public static final String EDGES = "graph.edges";
	public static final String AGGR = "aggregates";

	public static final String NAME = "name";
	public static final String ISN = "ISN";
	public static final String SRC = "SourceISN";
	public static final String OBJ = "ObjectISN";

	// private final PhoneMeFrame parentFrame;

	public RelationNetShow(final PhoneMeFrame parentFrame) {
		// initialize display and data
		super(new Visualization());
		// this.parentFrame = parentFrame;
		initDataGroups();

		// set up the renderers
		// draw the nodes as basic shapes
		LabelRenderer nodeR = new LabelRenderer(NAME);
		nodeR.setRenderType(AbstractShapeRenderer.RENDER_TYPE_FILL);
		nodeR.setHorizontalAlignment(Constants.CENTER);
		nodeR.setRoundedCorner(8, 8);
		// draw aggregates as polygons with curved edges
		Renderer polyR = new PolygonRenderer(Constants.POLY_TYPE_CURVE);
		((PolygonRenderer) polyR).setCurveSlack(0.15f);

		DefaultRendererFactory drf = new DefaultRendererFactory(nodeR);
		drf.add("ingroup('aggregates')", polyR);
		m_vis.setRendererFactory(drf);

		// set up the visual operators
		// first set up all the color actions

		// NODE的文字部分
		ColorAction nNodeText = new ColorAction(NODES, VisualItem.TEXTCOLOR);
		nNodeText.setDefaultColor(ColorLib.gray(100));
		nNodeText.add("_hover", ColorLib.gray(50));

		// NODE的背景部分
		ColorAction nNodeFill = new ColorAction(NODES, VisualItem.FILLCOLOR);
		nNodeFill.setDefaultColor(ColorLib.gray(255));
		nNodeFill.add("_hover", ColorLib.gray(200));

		// 边的线条部分
		ColorAction nEdgesLine = new ColorAction(EDGES, VisualItem.STROKECOLOR);
		nEdgesLine.setDefaultColor(ColorLib.gray(100));

		// 边的文字部分
		ColorAction nEdgesText = new ColorAction(EDGES, VisualItem.TEXTCOLOR);
		nEdgesText.setDefaultColor(ColorLib.gray(50));

		// 包络线的线条部分
		ColorAction aStroke = new ColorAction(AGGR, VisualItem.STROKECOLOR);
		aStroke.setDefaultColor(ColorLib.gray(200));
		aStroke.add("_hover", ColorLib.rgb(255, 100, 100));

		int[] palette = new int[] { ColorLib.rgba(255, 200, 200, 150),
				ColorLib.rgba(200, 255, 200, 150),
				ColorLib.rgba(200, 200, 255, 150) };
		ColorAction aFill = new DataColorAction(AGGR, "id", Constants.NOMINAL,
				VisualItem.FILLCOLOR, palette);

		// bundle the color actions
		ActionList colors = new ActionList();
		colors.add(nNodeText);
		colors.add(nNodeFill);
		colors.add(nEdgesLine);
		colors.add(nEdgesText);
		colors.add(aStroke);
		colors.add(aFill);

		// now create the main layout routine
		ActionList layout = new ActionList(Activity.INFINITY);
		layout.add(colors);
		layout.add(new ForceDirectedLayout(GRAPH, true));
		layout.add(new AggregateLayout(AGGR));
		layout.add(new RepaintAction());
		m_vis.putAction("layout", layout);

		// set up the display
		setSize(500, 500);
		pan(250, 250);
		setHighQuality(true);
		addControlListener(new AggregateDragControl(parentFrame));
		addControlListener(new ZoomControl());
		addControlListener(new PanControl());

		// set things running
		m_vis.run("layout");
	}

	@SuppressWarnings("unchecked")
	private void initDataGroups() {
		// TODO 初始化节点和边
		Schema nodeSchema = new Schema();
		Schema edgeSchema = new Schema();
		Table nodeTable;
		Table edgeTable;
		int rowNumber = -1;

		// 节点信息 Name ISN
		nodeSchema.addColumn(NAME, String.class);
		nodeSchema.addColumn(ISN, int.class);

		// 边信息 SourceISN ObjectISN Name
		edgeSchema.addColumn(SRC, int.class);
		edgeSchema.addColumn(OBJ, int.class);
		edgeSchema.addColumn(NAME, String.class);

		nodeSchema.lockSchema();
		edgeSchema.lockSchema();
		nodeTable = nodeSchema.instantiate();
		edgeTable = edgeSchema.instantiate();

		HashMap<Integer, ContactOperations> allContact = DataPool.getInstance()
				.getAllContactISNMap();

		HashMap<Integer, Integer> myISNNodeMap = new HashMap<Integer, Integer>();
		for (ContactOperations c : allContact.values()) {
			if (c.getRelationLabelList().size() > 0) {
				// SRC Node
				rowNumber = nodeTable.addRow();
				nodeTable.set(rowNumber, NAME, c.getContactName());
				nodeTable.set(rowNumber, ISN, c.getISN());

				myISNNodeMap.put(c.getISN(), new Integer(rowNumber));
				for (RelationLabel r : c.getRelationLabelList()) {
					// OBJ Node
					rowNumber = nodeTable.addRow();
					nodeTable.setString(rowNumber, NAME, allContact.get(
							r.getRelationObjectISN()).getContactName());
					nodeTable.set(rowNumber, ISN, r.getRelationObjectISN());

					myISNNodeMap.put(r.getRelationObjectISN(), new Integer(
							rowNumber));

					// Edge
					rowNumber = edgeTable.addRow();
					edgeTable.set(rowNumber, SRC, myISNNodeMap.get(c.getISN()));
					edgeTable.set(rowNumber, OBJ, myISNNodeMap.get(r
							.getRelationObjectISN()));
					edgeTable.setString(rowNumber, NAME, allContact.get(
							r.getRelationObjectISN()).getContactName());
				}
			}
		}

		Graph g = new Graph(nodeTable, edgeTable, false, SRC, OBJ);
		// g.putClientProperty(ISN, ISN);

		// add visual data groups
		VisualGraph vg = m_vis.addGraph(GRAPH, g);
		m_vis.setInteractive(EDGES, null, false);
		m_vis.setValue(NODES, null, VisualItem.SHAPE, new Integer(
				Constants.SHAPE_ELLIPSE));

		AggregateTable at = m_vis.addAggregates(AGGR);
		at.addColumn(VisualItem.POLYGON, float[].class);
		at.addColumn("id", int.class);
		at.addColumn(NAME, String.class);
		at.addColumn(ISN, int.class);

		Iterator nodes = vg.nodes();
		for (int i = 0; i < 1; ++i) {
			AggregateItem aitem = (AggregateItem) at.addItem();
			aitem.setInt("id", i);
			for (int j = 0; j < vg.getNodeCount(); ++j) {
				aitem.addItem((VisualItem) nodes.next());
			}
		}

	}

}

/**
 * Layout algorithm that computes a convex hull surrounding aggregate items and
 * saves it in the "_polygon" field.
 */
class AggregateLayout extends Layout {

	private int m_margin = 5; // convex hull pixel margin
	private double[] m_pts; // buffer for computing convex hulls

	public AggregateLayout(String aggrGroup) {
		super(aggrGroup);
	}

	/**
	 * @see edu.berkeley.guir.prefuse.action.Action#run(edu.berkeley.guir.prefuse.ItemRegistry,
	 *      double)
	 */
	@SuppressWarnings("unchecked")
	public void run(double frac) {

		AggregateTable aggr = (AggregateTable) m_vis.getGroup(m_group);
		// do we have any to process?
		int num = aggr.getTupleCount();
		if (num == 0)
			return;

		// update buffers
		int maxsz = 0;
		for (Iterator aggrs = aggr.tuples(); aggrs.hasNext();)
			maxsz = Math.max(maxsz, 4 * 2 * ((AggregateItem) aggrs.next())
					.getAggregateSize());
		if (m_pts == null || maxsz > m_pts.length) {
			m_pts = new double[maxsz];
		}

		// compute and assign convex hull for each aggregate
		Iterator aggrs = m_vis.visibleItems(m_group);
		while (aggrs.hasNext()) {
			AggregateItem aitem = (AggregateItem) aggrs.next();

			int idx = 0;
			if (aitem.getAggregateSize() == 0)
				continue;
			VisualItem item = null;
			Iterator iter = aitem.items();
			while (iter.hasNext()) {
				item = (VisualItem) iter.next();
				if (item.isVisible()) {
					addPoint(m_pts, idx, item, m_margin);
					idx += 2 * 4;
				}
			}
			// if no aggregates are visible, do nothing
			if (idx == 0)
				continue;

			// compute convex hull
			double[] nhull = GraphicsLib.convexHull(m_pts, idx);

			// prepare viz attribute array
			float[] fhull = (float[]) aitem.get(VisualItem.POLYGON);
			if (fhull == null || fhull.length < nhull.length)
				fhull = new float[nhull.length];
			else if (fhull.length > nhull.length)
				fhull[nhull.length] = Float.NaN;

			// copy hull values
			for (int j = 0; j < nhull.length; j++)
				fhull[j] = (float) nhull[j];
			aitem.set(VisualItem.POLYGON, fhull);
			aitem.setValidated(false); // force invalidation
		}
	}

	private static void addPoint(double[] pts, int idx, VisualItem item,
			int growth) {
		Rectangle2D b = item.getBounds();
		double minX = (b.getMinX()) - growth, minY = (b.getMinY()) - growth;
		double maxX = (b.getMaxX()) + growth, maxY = (b.getMaxY()) + growth;
		pts[idx] = minX;
		pts[idx + 1] = minY;
		pts[idx + 2] = minX;
		pts[idx + 3] = maxY;
		pts[idx + 4] = maxX;
		pts[idx + 5] = minY;
		pts[idx + 6] = maxX;
		pts[idx + 7] = maxY;
	}

}

/**
 * Interactive drag control that is "aggregate-aware"
 */
class AggregateDragControl extends ControlAdapter {

	private VisualItem activeItem;
	private final PhoneMeFrame pareFrame;
	protected Point2D down = new Point2D.Double();
	protected Point2D temp = new Point2D.Double();
	protected boolean dragged;

	/**
	 * Creates a new drag control that issues repaint requests as an item is
	 * dragged.
	 */
	public AggregateDragControl(final PhoneMeFrame pareFrame) {
		this.pareFrame = pareFrame;
	}

	/**
	 * @see prefuse.controls.Control#itemEntered(prefuse.visual.VisualItem,
	 *      java.awt.event.MouseEvent)
	 */
	public void itemEntered(VisualItem item, MouseEvent e) {
		Display d = (Display) e.getSource();
		d.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		activeItem = item;
		if (!(item instanceof AggregateItem))
			setFixed(item, true);
	}

	/**
	 * @see prefuse.controls.Control#itemExited(prefuse.visual.VisualItem,
	 *      java.awt.event.MouseEvent)
	 */
	public void itemExited(VisualItem item, MouseEvent e) {
		if (activeItem == item) {
			activeItem = null;
			setFixed(item, false);
		}
		Display d = (Display) e.getSource();
		d.setCursor(Cursor.getDefaultCursor());
	}

	/**
	 * @see prefuse.controls.Control#itemPressed(prefuse.visual.VisualItem,
	 *      java.awt.event.MouseEvent)
	 */
	public void itemPressed(VisualItem item, MouseEvent e) {
		if (!SwingUtilities.isLeftMouseButton(e))
			return;
		dragged = false;
		Display d = (Display) e.getComponent();
		d.getAbsoluteCoordinate(e.getPoint(), down);
		if (item instanceof AggregateItem)
			setFixed(item, true);
		
		System.out.println("ISN = " + item.getInt(RelationNetShow.ISN)
				+ " Item Pressed");
		
		ContactOperations c = DataPool.getInstance().getAllContactISNMap().get(
				item.getInt(RelationNetShow.ISN));
		
		// TODO if c == null

		pareFrame.getMyPhoneMeMajorPanel().addNewTab(
				c.getContactName(),
				new ContactInfoPanel(pareFrame, c, false,
						ContactInfoPanel.CONTACT_INFO_PANEL));
	}

	/**
	 * @see prefuse.controls.Control#itemReleased(prefuse.visual.VisualItem,
	 *      java.awt.event.MouseEvent)
	 */
	public void itemReleased(VisualItem item, MouseEvent e) {
		if (!SwingUtilities.isLeftMouseButton(e))
			return;
		if (dragged) {
			activeItem = null;
			setFixed(item, false);
			dragged = false;
		}
	}

	/**
	 * @see prefuse.controls.Control#itemDragged(prefuse.visual.VisualItem,
	 *      java.awt.event.MouseEvent)
	 */
	public void itemDragged(VisualItem item, MouseEvent e) {
		if (!SwingUtilities.isLeftMouseButton(e))
			return;
		dragged = true;
		Display d = (Display) e.getComponent();
		d.getAbsoluteCoordinate(e.getPoint(), temp);
		double dx = temp.getX() - down.getX();
		double dy = temp.getY() - down.getY();

		move(item, dx, dy);

		down.setLocation(temp);
	}

	@SuppressWarnings("unchecked")
	protected static void setFixed(VisualItem item, boolean fixed) {
		if (item instanceof AggregateItem) {
			Iterator items = ((AggregateItem) item).items();
			while (items.hasNext()) {
				setFixed((VisualItem) items.next(), fixed);
			}
		} else {
			item.setFixed(fixed);
		}
	}

	@SuppressWarnings("unchecked")
	protected static void move(VisualItem item, double dx, double dy) {
		if (item instanceof AggregateItem) {
			Iterator items = ((AggregateItem) item).items();
			while (items.hasNext()) {
				move((VisualItem) items.next(), dx, dy);
			}
		} else {
			double x = item.getX();
			double y = item.getY();
			item.setStartX(x);
			item.setStartY(y);
			item.setX(x + dx);
			item.setY(y + dy);
			item.setEndX(x + dx);
			item.setEndY(y + dy);
		}
	}
}