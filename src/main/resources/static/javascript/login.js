function initializeLoginLayout() {

    var height = window.innerHeight - document.getElementById("footer").offsetHeight;
    var loginHeight = parseInt(document.getElementsByClassName("login")[0].offsetHeight);

    if(loginHeight < height) {

        var paddingTop = (height - loginHeight) / 2;
        document.getElementsByClassName("login")[0].style.paddingTop = paddingTop + "px";

        height = loginHeight + paddingTop + document.getElementById("footer").offsetHeight;

        if(height < window.innerHeight) {

            var paddingBottom = window.innerHeight - height;
            document.getElementsByClassName("login")[0].style.paddingBottom = paddingBottom + "px";

        }

    } else {

        document.getElementsByClassName("login")[0].removeAttribute("style");

    }

}


document.addEventListener("DOMContentLoaded", function(event) {


    initializeLoginLayout();


    document.getElementsByClassName("username")[0].onload = function() {

        document.getElementsByClassName("username")[0].focus();

    };


});


window.addEventListener("resize", function(event) {


    initializeLoginLayout();


});


app.controller("login", ["$scope", "global", function($scope, global) {


    $scope.account = {
        "avatar": document.getElementById("global").getAttribute("data-image-url") + "/user/user-picture.png",
        "name": "Guest"
    };

    $scope.password = {
        "value": "",
        "view": false
    };

    $scope.username = {
        "value": "",
        "view": true
    };


    $scope.backward = function() {

        $scope.response.view = false;
        $scope.username.view = true;
        $scope.password.view = false;
        $scope.account.avatar = document.getElementById("global").getAttribute("data-image-url") + "/user/user-picture.png";
        $scope.account.name = "Guest";

        angular.element(document).ready(function() {

            document.getElementsByClassName("username")[0].focus();

        });

    }


    $scope.checkPassword = function(event) {

        if(event.which == 1 || event.which == 13) {

            $scope.loading.view = true;

            var rest = {
                "data": {
                    "password": {
                        "main": library.encryption.rsaEncrypt($scope.password.value)
                    },
                    "username": $scope.username.value
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/login/check-password"
            };
            global.rest(rest, function(response) {

                if(response.result) {

                    angular.element(document).ready(function() {

                        document.getElementsByTagName("form")[0].submit();

                    });

                } else {

                    $scope.response.class = "error";
                    $scope.response.message = response.response;
                    $scope.response.view = true;

                }

                $scope.loading.view = false;

                $scope.hideResponse();

            });

            event.preventDefault();

        }

    }


    $scope.checkUsername = function(event) {

        if(event.which == 1 || event.which == 13) {

            $scope.loading.view = true;

            var rest = {
                "data": {
                    "username": $scope.username.value
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/login/check-username"
            };
            global.rest(rest, function(response) {

                if(response.result) {

                    $scope.response.view = false;
                    $scope.username.view = false;
                    $scope.password.view = true;

                    if(response.avatar != "") {

                        $scope.account.avatar = document.getElementById("global").getAttribute("data-image-url") + "/user/" + response.avatar;

                    }

                    $scope.account.name = response.name;

                    angular.element(document).ready(function() {

                        document.getElementsByClassName("password")[0].focus();

                    });

                } else {

                    $scope.response.class = "error";
                    $scope.response.message = response.response;
                    $scope.response.view = true;

                }

                $scope.loading.view = false;

                $scope.hideResponse();

            });

            event.preventDefault();

        }

    }


}]);
