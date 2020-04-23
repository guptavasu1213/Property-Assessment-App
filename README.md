# Property Assessment Data Application

## Overview
This app uses [City of Edmonton property assessment data](https://data.edmonton.ca/City-Administration/Property-Assessment-Data-2012-2019-/qi6a-xuwt) makes it possible for user to extract and visualize information easily.

The data is extracted via Socrata Open Data API (SODA) from the Edmonton Property Assessments data in real-time. 

The property data is displayed in a tabular for and graphical form. 
* The Table tab shows all the Edmonton property data. 
* The Visualization tab has 3 different modes:
  * Viewing a Pie Chart: The pie chart represents the 6 groups of property assessment values. The 6 groups are as follows: $0 to $30000, $30000 to $70000, $70000 to $120000, $120000 to 200000, 200000 to 500000, and 500000 and above.
  * Viewing a Bar Chart: The bar chart represents all the wards and their average property assessment values. Each bar on the graph represents a ward.
  * Viewing a Scatter Plot: The scatter plot represents all the neighborhoods and their average property assessment values. Each point on the graph represents a neighborhood.

<p float="left">
  <img src="https://github.com/guptavasu1213/property-assessment-app/blob/master/PieChart.png" width="300" />
  <img src="https://github.com/guptavasu1213/property-assessment-app/blob/master/barGraph.png" width="300" /> 
  <img src="https://github.com/guptavasu1213/property-assessment-app/blob/master/ScatterPlot.png" width="300" />
</p>

This allows the user to have a visual representation of the property assessment values by viewing the data for neighborhood, wards and the overall assessment values.
These visualizations are shown based on the filtered results using the left-hand-side search box.

## Prerequisites
- Java 8
- This project was created using Netbeans 8.2 project

## Status
The project is completed.
