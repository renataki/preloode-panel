function initializeShutdownLayout() {

    var shutdown = document.getElementsByClassName("shutdown");

    if(shutdown.length > 0) {

        var height = window.innerHeight - document.getElementById("footer").offsetHeight;
        var shutdownHeight = shutdown[0].offsetHeight;

        if(shutdownHeight < height) {

            var padding = (height - shutdownHeight) / 2;
            shutdown[0].style.paddingTop = padding + "px";

            height = shutdownHeight + document.getElementById("footer").offsetHeight;

            if(window.innerHeight > height) {

                padding = window.innerHeight - height;
                shutdown[0].style.paddingBottom = padding + "px";

            }

        } else {

            shutdown[0].removeAttribute("style");

        }

    }

}


document.addEventListener("DOMContentLoaded", function(event) {


    initializeShutdownLayout();


});


window.addEventListener("resize", function(event) {


    initializeShutdownLayout();


});


app.controller("shutdown", ["$scope", "$window", "global", function($scope, $window, global) {


    $scope.shutdown = function(event) {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/actuator/shutdown"
        }
        global.rest(rest, function(response) {

            $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/";

        });

        event.preventDefault();

    }


}]);
