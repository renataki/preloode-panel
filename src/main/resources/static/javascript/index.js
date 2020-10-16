app.controller("dashboard", ["$scope", "global", function($scope, global) {


    $scope.initializeChart = function() {

        angular.element(document).ready(function() {

            var data = [
                {"action": "Add To Cart", "index": 1, "percentage": 15},
                {"action": "Checkout", "index": 2, "percentage": 25},
                {"action": "Deposit", "index": 3, "percentage": 10},
                {"action": "Registration", "index": 4, "percentage": 30},
                {"action": "Withdrawal", "index": 5, "percentage": 20}
            ];

            var height = parseInt(window.getComputedStyle(document.getElementsByClassName("chart-action-pie")[0]).height);
            var width = parseInt(window.getComputedStyle(document.getElementsByClassName("chart-action-pie")[0]).width);
            var innerRadius = 0;
            var outerRadius = Math.min(width, height) / 2;
            var legendHeight = 30;
            var legendSpace = 70;
            var rectangleSize = 20;
            var animationDuration = 200;

            var wrapper = d3.select(".chart-action-pie").attr("width", width).attr("height", height);
            var chart = wrapper.append("g").attr("transform", "translate(" + outerRadius + ", " + outerRadius + ")");
            var color = d3.scaleOrdinal(d3.schemeCategory10);

            var pie = d3.pie().value(function(d) {

                return d.percentage;

            });

            var arc = d3.arc().outerRadius(outerRadius).innerRadius(innerRadius);
            var arcHover = d3.arc().outerRadius(outerRadius + 10).innerRadius(innerRadius);
            var arcGroup = chart.selectAll("arc").data(pie(data)).enter().append("g");

            var path = arcGroup.append("path").attr("d", arc).attr("class", function(d) {

                return "path-" + d.data.index;

            }).attr("fill", function(d) {

                return color(d.data.index);

            });

            path.on("mouseenter", function(d) {

                d3.select(this).transition().duration(animationDuration).attr("d", arcHover);

            });

            path.on("mouseleave", function(d) {

                d3.select(this).transition().duration(animationDuration).attr("d", arc);

            });

            var label = d3.arc().outerRadius(outerRadius).innerRadius(innerRadius);
            var text = arcGroup.append("text").attr("text-anchor", "middle").attr("transform", function(d) {

                return "translate(" + label.centroid(d) + ")";

            }).text(function(d) {

                return d.data.percentage + "%";

            });

            text.on("mouseenter", function(d) {

                d3.select(".path-" + d.data.index).transition().duration(animationDuration).attr("d", arcHover);

            });

            text.on("mouseleave", function(d) {

                d3.select(".path-" + d.data.index).transition().duration(animationDuration).attr("d", arc);

            });

            var legend = chart.selectAll(".legend").data(pie(data)).enter().append("g").attr("class", "legend").attr("transform", function(d, i) {

                return "translate(" + legendSpace + ", " + (parseInt("-" + (data.length * 10)) + i * legendHeight + 0) + ")";

            });

            legend.append("rect").attr("x", width / 2).attr("width", rectangleSize).attr("height", rectangleSize).style("fill", function(d) {

                return color(d.data.index);

            });

            legend.append("text").attr("x", (width / 2) - 5).attr("y", 10).attr("dy", ".35em").style("text-anchor", "end").text(function(d) {

                return d.data.action;

            });

            legend.on("mouseenter", function(d) {

                d3.select(".path-" + d.data.index).transition().duration(animationDuration).attr("d", arcHover);

            });

            legend.on("mouseleave", function(d) {

                d3.select(".path-" + d.data.index).transition().duration(animationDuration).attr("d", arc);

            });

        });

    }


}]);
