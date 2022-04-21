package weka.clusterers;
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author code
 */
public class GraphPlotter extends ApplicationFrame implements Serializable {

	/** Serialization */
	static final long serialVersionUID = -401133168492661320L;

	/** Default constructor. */
	public GraphPlotter(String windowTitle){
		super(windowTitle);
	}

	/**
	 * Plots a line chart for cascade mode given the following
	 * parameters.
	 *
	 * @param dataSet  Y value point to be plotted.
	 * @param startK   When in cascade mode, defines the initial K.
	 * @param title    Graph title.
	 * @param subTitle Graph subtitle.
	 * @param xLabel   Graph X label.
	 * @param yLabel   Graph Y label.
	 */
	public void plot(ArrayList<Double> dataSet, int startK, String title,
		String subTitle, String xLabel, String yLabel) {

		/* Mounts the dataset. */
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		for (int i = 0; i < dataSet.size(); i++)
			data.addValue( dataSet.get(i), "k",  "" + (startK + i) );

		JFreeChart chart = ChartFactory.createLineChart(title, xLabel, yLabel,
			data, PlotOrientation.VERTICAL, false, true, false);

		/* Title and background. */
		chart.addSubtitle(new TextTitle(subTitle));
		chart.setBackgroundPaint(Color.white);

		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.white);

		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		renderer.setSeriesShapesVisible(0, true);
		renderer.setSeriesPaint(0, Color.blue);
		renderer.setDrawOutlines(true);
		renderer.setUseFillPaint(true);

		/* Shows the chart. */
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(412, 323));
		setContentPane(chartPanel);

		pack();
		RefineryUtilities.centerFrameOnScreen(this);
		setVisible(true);
	}
}
