function initializeInstallationLayout() {

    var installation = document.getElementsByClassName("installation");

    if(installation.length > 0) {

        var height = window.innerHeight - document.getElementById("footer").offsetHeight;
        var installationHeight = installation[0].offsetHeight;

        if(installationHeight < height) {

            var padding = (height - installationHeight) / 2;
            installation[0].style.paddingTop = padding + "px";

            height = installationHeight + document.getElementById("footer").offsetHeight;

            if(window.innerHeight > height) {

                padding = window.innerHeight - height;
                installation[0].style.paddingBottom = padding + "px";

            }

        } else {

            installation[0].removeAttribute("style");

        }

    }

}


document.addEventListener("DOMContentLoaded", function(event) {


    initializeInstallationLayout();


});


window.addEventListener("resize", function(event) {


    initializeInstallationLayout();


});


app.controller("installation", ["$scope", "$window", "global", function($scope, $window, global) {


    $scope.redirect = false;

    $scope.status = "Installation";


    $scope.goToPanel = function(event) {

        $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/";

        event.preventDefault();

    }


    $scope.initializeDemoData = function(event) {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/installation/initialize-demo-data"
        }
        global.rest(rest, function(response) {

            if(response.result) {

                $scope.redirect = true;

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/";

            } else {

                $scope.response.class = "error";
                $scope.response.message = response.response;
                $scope.response.view = true;

                $scope.hideResponse();

            }

            $scope.loading.view = false;

        });

        if(!$scope.redirect) {

            event.preventDefault();

        }

    }


    $scope.install = function(event) {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/installation/install"
        }
        global.rest(rest, function(response) {

            if(response.result) {

                $scope.status = "Initialize Demo Data";

            } else {

                $scope.response.class = "error";
                $scope.response.message = response.response;
                $scope.response.view = true;

                $scope.hideResponse();

            }

            $scope.loading.view = false;

        });

        event.preventDefault();

    }


    $scope.uninstall = function(event) {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/installation/uninstall"
        }
        global.rest(rest, function(response) {

            if(response.result) {

                $scope.redirect = true;

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/installation/";

            } else {

                $scope.response.class = "error";
                $scope.response.message = response.response;
                $scope.response.view = true;

                $scope.hideResponse();

            }

            $scope.loading.view = false;

        });

        if(!$scope.redirect) {

            event.preventDefault();

        }

    }


}]);
