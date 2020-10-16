var library = new classLibrary();


function preventRestoreOriginalView() {

    var anchor = Array.prototype.slice.call(document.getElementsByTagName("a"));

    anchor.forEach(function(value, key) {

        value.addEventListener("click", function(event) {

            event.stopPropagation();

        });

    });

    var input = Array.prototype.slice.call(document.getElementsByTagName("input"));

    input.forEach(function(value, key) {

        value.addEventListener("click", function(event) {

            event.stopPropagation();

        });

    });

    var button = Array.prototype.slice.call(document.getElementsByTagName("button"));

    button.forEach(function(value, key) {

        value.addEventListener("click", function(event) {

            event.stopPropagation();

        });

    });

}


function restoreOriginalView(event) {

    if(event.button == 0) {

        var menu = document.getElementById("menu");

        if(menu) {

            var position = document.getElementById("menu").getBoundingClientRect();
            var width = document.getElementById("menu").clientWidth;
            var iconWidth = document.getElementById("menu-icon").getElementsByTagName("i")[0].clientWidth;
            var iconPadding = parseInt(window.getComputedStyle(document.getElementById("menu-icon"), null).getPropertyValue("padding-right")) * 2;

            if(position.left == 0) {

                document.getElementById("menu").style.transform = "translateX(-100%)";
                document.getElementById("menu-icon").style.transform = "translateX(-" + (width - (iconWidth + iconPadding)) + "px)";

                var now = new Date();
                var expires = now.setTime(now.getTime() + (365 * 24 * 60 * 60 * 1000));
                expires = new Date(expires);
                document.cookie = document.getElementById("global").getAttribute("data-cookie-prefix") + "mnmnu=" + JSON.stringify("Closed") + "; expires=" + expires + "; path=" + document.getElementById("global").getAttribute("data-cookie-path");

            }

        }

    }

}


function toggleChildMenu(element) {

    var index = element.parentNode.getAttribute("data-index");

    var childMenu = document.getElementsByClassName("child-menu");

    for(var i = 0; i < childMenu.length; i++) {

        if(childMenu[i].getAttribute("data-index") == index) {

            if(childMenu[i].style.height == "40px" || childMenu[i].style.height == "3px") {

                childMenu[i].style.height = 0;
                childMenu[i].style.opacity = 0;
                element.getElementsByTagName("i")[0].style.transform = "none";

            } else {

                if(!/separator/.test(childMenu[i].getAttribute("class"))) {

                    childMenu[i].style.height = "40px";
                    childMenu[i].style.opacity = 1;

                } else {

                    childMenu[i].style.height = "3px";

                }

                element.getElementsByTagName("i")[0].style.transform = "rotate(90deg)";

            }

        }

    }

}


function toggleMenu(element) {

    var cookie = "";

    var position = document.getElementById("menu").getBoundingClientRect();
    var width = document.getElementById("menu").clientWidth;
    var iconWidth = element.getElementsByTagName("i")[0].clientWidth;
    var iconPadding = parseInt(window.getComputedStyle(element, null).getPropertyValue("padding-right")) * 2;

    if(position.left < 0) {

        document.getElementById("menu").style.transform = "translateX(0)";
        element.style.transform = "translateX(0)";

        cookie = "opn";

    } else {

        document.getElementById("menu").style.transform = "translateX(-100%)";
        element.style.transform = "translateX(-" + (width - (iconWidth + iconPadding)) + "px)";

        cookie = "cls";

    }

    var now = new Date();
    var expires = now.setTime(now.getTime() + (365 * 24 * 60 * 60 * 1000));
    expires = new Date(expires);
    document.cookie = document.getElementById("global").getAttribute("data-cookie-prefix") + "mnmnu=" + JSON.stringify(cookie) + "; expires=" + expires + "; path=" + document.getElementById("global").getAttribute("data-cookie-path");

}


document.addEventListener("DOMContentLoaded", function(event) {


    new Date().toLocaleString(document.getElementById("global").getAttribute("data-datetime-format"), {timeZone: document.getElementById("global").getAttribute("data-datetime-timezone")});


    library.layout.initialize();


    var menu = document.getElementById("menu");
    var menuIcon = document.getElementById("menu-icon");

    if(menuIcon) {

        menuIcon.addEventListener("click", function(event) {

            event.stopPropagation();

            toggleMenu(this);

        });

    }

    if(menu) {

        menu.addEventListener("click", function(event) {

            event.stopPropagation();

        });

    }


    var toggle = document.getElementsByClassName("menu-toggle");

    for(var i = 0; i < toggle.length; i++) {

        toggle[i].addEventListener("click", function() {

            toggleChildMenu(this);

        });

    }


    flatpickr(".date-picker", {
        "dateFormat": "Y-m-d",
    });

    flatpickr(".date-range-picker", {
        "dateFormat": "Y-m-d",
        "mode": "range"
    });

    flatpickr(".date-time-picker", {
        "dateFormat": "Y-m-d H:i:ss",
        "enableTime": true,
    });

    flatpickr(".time-picker", {
        "dateFormat": "H:i",
        "enableTime": true,
        "noCalendar": true
    });


    var sentenceCase = Array.prototype.slice.call(document.getElementsByClassName("sentence-case"));

    sentenceCase.forEach(function(value, key) {

        value.innerHTML = value.innerHTML.replace(/([A-Z])/g, ' $1').trim();

    });


    library.accordion.initialize("accordion", {
        "openOnlyOneAtATime": false
    });


    preventRestoreOriginalView();


    document.addEventListener("click", function(event) {

        restoreOriginalView(event);

    });


});


window.addEventListener("resize", function(event) {


    library.layout.initialize();


});


var app = angular.module("app", ["ngMaterial", "ngSanitize", "ngAnimate", "ngScrollbar"]);


app.config(["$httpProvider", function($httpProvider) {


    $httpProvider.defaults.headers.post["X-XSRF-TOKEN"] = '';
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common["X-Requested-With"];


}]);


app.controller("global", ["$scope", "$parse", "$timeout", "$window", "global", function($scope, $parse, $timeout, $window, global) {


    $scope.activation = {
        "email": {
            "name": "",
            "value": false
        },
        "sms": {
            "name": "",
            "value": false
        }
    };

    $scope.company = {
        "branch": {
            "idList": {
                "value": []
            },
            "nameList": {
                "value": []
            },
            "option": [],
            "response": {
                "class": "",
                "value": "",
                "view": false
            }
        },
        "idList": {
            "value": []
        },
        "nameList": {
            "value": []
        },
        "option": [],
        "response": {
            "class": "",
            "value": "",
            "view": false
        }
    };

    $scope.content = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.created = {
        "timestamp": {
            "value": "",
            "view": false
        },
        "user": {
            "id": {
                "value": ""
            },
            "username": {
                "value": ""
            }
        }
    };

    $scope.description = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.favicon = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "upload": {
            "file": "",
            "view": false
        },
        "value": ""
    };

    $scope.id = {
        "value": ""
    };

    $scope.imageList = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "upload": {
            "file": [],
            "view": false
        },
        "value": []
    };

    $scope.like = {
        "blog": {
            "value": "Inherit"
        },
        "shop": {
            "value": "Inherit"
        }
    };

    $scope.loading = {
        "view": false
    };

    $scope.logo = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "upload": {
            "file": "",
            "view": false
        },
        "value": ""
    };

    $scope.meta = {
        "description": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "keyword": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "title": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        }
    };

    $scope.modified = {
        "timestamp": {
            "value": "",
            "view": false
        },
        "user": {
            "id": {
                "value": ""
            },
            "username": {
                "value": ""
            }
        }
    };

    $scope.og = {
        "description": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "title": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        }
    };

    $scope.popup = {
        "blogCategory": false,
        "blogPost": false,
        "blogStar": false,
        "company": false,
        "companyBranch": false,
        "crmDatabase": false,
        "crmDatabaseSource": false,
        "cryptocurrencyTriangularArbitrage": false,
        "paymentAccount": false,
        "paymentMethod": false,
        "shopBrand": false,
        "shopCategory": false,
        "shopProduct": false,
        "thirdPartyAccount": false,
        "thirdPartyProvider": false,
        "user": false,
        "userGroup": false,
        "userRole": false,
        "view": false
    };

    $scope.privilege = {
        "blog": {
            "name": "Blog",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "blogCategory": {
            "name": "Blog Category",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "blogPost": {
            "name": "Blog Post",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "blogStar": {
            "name": "Blog Star",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "company": {
            "name": "Company",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "companyBranch": {
            "name": "Company Branch",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "crm": {
            "name": "CRM",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "crmDatabase": {
            "name": "CRM Database",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "crmDatabaseSource": {
            "name": "CRM Database Source",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "crmEmail": {
            "name": "CRM Email",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "crmEmailBlast": {
            "name": "CRM Email Blast",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "crmGroup": {
            "name": "CRM Group",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "crmPhone": {
            "name": "CRM Phone",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "crmPhoneCall": {
            "name": "CRM Phone Call",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "crmWhatsapp": {
            "name": "CRM Whatsapp",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "crmWhatsappBlast": {
            "name": "CRM Whatsapp Blast",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "cryptocurrency": {
            "name": "Cryptocurrency",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "cryptocurrencyArbitrage": {
            "name": "Cryptocurrency Arbitrage",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "cryptocurrencyTriangularArbitrage": {
            "name": "Cryptocurrency Triangular Arbitrage",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "payment": {
            "name": "Payment",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "paymentAccount": {
            "name": "Payment Account",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "paymentMethod": {
            "name": "Payment Method",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "setting": {
            "name": "Setting",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "settingSlider": {
            "name": "Setting Slider",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "shop": {
            "name": "Shop",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "shopBrand": {
            "name": "Shop Brand",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "shopCategory": {
            "name": "Shop Category",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "shopProduct": {
            "name": "Shop Product",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "thirdParty": {
            "name": "Third Party",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "thirdPartyAccount": {
            "name": "Third Party Account",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "thirdPartyProvider": {
            "name": "Third Party Provider",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "transaction": {
            "name": "Transaction",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "transactionCart": {
            "name": "Transaction Cart",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "user": {
            "name": "User",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "userGroup": {
            "name": "User Group",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        },
        "userRole": {
            "name": "User Role",
            "option": [
                {"name": "View"},
                {"name": "Insert"},
                {"name": "Edit"},
                {"name": "Delete"}
            ],
            "status": "Unchecked All",
            "value": "0000"
        }
    };

    $scope.privilegeAll = {
        "option": [
            {"name": "View", "status": "Unchecked All"},
            {"name": "Insert", "status": "Unchecked All"},
            {"name": "Edit", "status": "Unchecked All"},
            {"name": "Delete", "status": "Unchecked All"}
        ],
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "status": "Unchecked All",
        "value": "0000"
    };

    $scope.reference = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.rating = {
        "blog": {
            "value": "Inherit"
        },
        "shop": {
            "value": "Inherit"
        }
    };

    $scope.response = {
        "class": "",
        "message": "",
        "view": false
    };

    $scope.review = {
        "blog": {
            "value": "Inherit"
        },
        "shop": {
            "value": "Inherit"
        }
    };

    $scope.site = {
        "page": {
            "number": "",
            "size": ""
        }
    };

    $scope.status = {
        "option": [
            {"name": "Status", "value": "Unknown"}
        ],
        "response": {
            "class": "",
            "value": "",
            "view": false
        }
    };

    $scope.status.selected = $scope.status.option[0];

    $scope.streetAddress = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.thumbnailList = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "upload": {
            "file": [],
            "view": false
        },
        "value": []
    };

    $scope.valid = {
        "companyBranch": false,
        "company": false,
        "status": false
    };


    $scope.camelCaseToStandardCase = function(string) {

        return string.replace(/([A-Z]+)/g, " $1").replace(/([A-Z][a-z])/g, " $1").replace(/([0-9]+)/g, " $1").trim();

    }


    $scope.checkCompany = function() {

        if($scope.company.idList.value.length == 0) {

            $scope.company.response.value = "Please select at least 1 company";
            $scope.company.response.class = "error";
            $scope.company.response.view = true;
            $scope.valid.company = false;

        } else {

            $scope.company.response.view = false;
            $scope.valid.company = true;

        }

    }


    $scope.checkCompanyBranch = function() {

        if($scope.company.branch.idList.value.length == 0) {

            $scope.company.branch.response.value = "Please select at least 1 company branch";
            $scope.company.branch.response.class = "error";
            $scope.company.branch.response.view = true;
            $scope.valid.companyBranch = false;

        } else {

            $scope.company.branch.response.view = false;
            $scope.valid.companyBranch = true;

        }

    }


    $scope.checkStatus = function() {

        if($scope.status.selected.value == "Unknown") {

            $scope.status.response.value = "Please select status";
            $scope.status.response.class = "error";
            $scope.status.response.view = true;
            $scope.valid.status = false;

        } else {

            $scope.status.response.view = false;
            $scope.valid.status = true;

        }

    }


    $scope.closePopup = function() {

        angular.forEach($scope.popup, function(value, key) {

            $scope.popup[key] = false;

        });

    }


    $scope.companyBranchToggleAllCheckbox = function() {

        if($scope.company.branch.option.length > $scope.company.branch.idList.value.length) {

            $scope.company.branch.idList.value = [];
            $scope.company.branch.nameList.value = [];

            angular.forEach($scope.company.branch.option, function(value, key) {

                $scope.company.branch.idList.value.push(value.id);
                $scope.company.branch.nameList.value.push(value.name);

            });

        } else {

            $scope.company.branch.idList.value = [];
            $scope.company.branch.nameList.value = [];

        }

    }


    $scope.companyBranchToggleCheckbox = function(companyBranch) {

        var index = $scope.company.branch.idList.value.indexOf(companyBranch.id);

        if(index > -1) {

            $scope.company.branch.idList.value.splice(index, 1);
            $scope.company.branch.nameList.value.splice(index, 1);

        } else {

            $scope.company.branch.idList.value.push(companyBranch.id);
            $scope.company.branch.nameList.value.push(companyBranch.name);

        }

    }


    $scope.companyToggleAllCheckbox = function() {

        if($scope.company.option.length > $scope.company.idList.value.length) {

            $scope.company.idList.value = [];
            $scope.company.nameList.value = [];

            angular.forEach($scope.company.option, function(value, key) {

                $scope.company.idList.value.push(value.id);
                $scope.company.nameList.value.push(value.name);

            });

        } else {

            $scope.company.idList.value = [];
            $scope.company.nameList.value = [];

        }

    }


    $scope.companyToggleCheckbox = function(company) {

        var index = $scope.company.idList.value.indexOf(company.id);

        if(index > -1) {

            $scope.company.idList.value.splice(index, 1);
            $scope.company.nameList.value.splice(index, 1);

        } else {

            $scope.company.idList.value.push(company.id);
            $scope.company.nameList.value.push(company.name);

        }

    }


    $scope.filterEndWith = function(data, filter) {

        return data.toLowerCase().endsWith(filter.toLowerCase());

    }


    $scope.filterMatch = function(data, filter) {

        return data.toLowerCase() == filter.toLowerCase();

    }


    $scope.filterStartWith = function(data, filter) {

        return data.toLowerCase().startsWith(filter.toLowerCase());

    }


    $scope.hideResponse = function() {

        $timeout(function() {

            $scope.response.view = false;
            $scope.$digest();

        }, 7000);

    }


    $scope.initializeLayout = function() {

        angular.element(document).ready(function() {

            library.accordion.initialize("accordion", {
                "openOnlyOneAtATime": false
            });

            library.layout.initialize();

        });

    }


    $scope.initializePrivilege = function(privilege) {

        var checkedAll = {
            "all": true,
            "view": true,
            "insert": true,
            "edit": true,
            "delete": true
        };
        var uncheckedAll = {
            "all": true,
            "view": true,
            "insert": true,
            "edit": true,
            "delete": true
        };

        angular.forEach(privilege, function(value, key) {

            if(value != null) {

                $scope.privilege[key].value = value;

                if(value == "7777") {

                    $scope.privilege[key].status = "Checked All";

                } else if(value == "0000") {

                    $scope.privilege[key].status = "Unchecked All";

                } else {

                    $scope.privilege[key].status = "Checked In Part";

                }

                if(value != "7777") {

                    checkedAll.all = false;

                }

                if(value != "0000") {

                    uncheckedAll.all = false;

                }

                if(value.slice(0, 1) != "7") {

                    checkedAll.view = false;

                }

                if(value.slice(0, 1) != "0") {

                    uncheckedAll.view = false;

                }

                if(value.slice(1, 2) != "7") {

                    checkedAll.insert = false;

                }

                if(value.slice(1, 2) != "0") {

                    uncheckedAll.insert = false;

                }

                if(value.slice(2, 3) != "7") {

                    checkedAll.edit = false;

                }

                if(value.slice(2, 3) != "0") {

                    uncheckedAll.edit = false;

                }

                if(value.slice(3) != "7") {

                    checkedAll.delete = false;

                }

                if(value.slice(3) != "0") {

                    uncheckedAll.delete = false;

                }

            }

        });

        if(checkedAll.all && !uncheckedAll.all) {

            $scope.privilegeAll.status = "Checked All";
            $scope.privilegeAll.value = "7777";

        } else if(!checkedAll.all && uncheckedAll.all) {

            $scope.privilegeAll.status = "Unchecked All";
            $scope.privilegeAll.value = "0000";

        } else if(!checkedAll.all && !uncheckedAll.all) {

            $scope.privilegeAll.status = "Checked In Part";

        }

        if(checkedAll.view && !uncheckedAll.view) {

            $scope.privilegeAll.option[0].status = "Checked All";
            $scope.privilegeAll.value = "7" + $scope.privilegeAll.value.slice(1);

        } else if(!checkedAll.view && uncheckedAll.view) {

            $scope.privilegeAll.option[0].status = "Unchecked All";
            $scope.privilegeAll.value = "0" + $scope.privilegeAll.value.slice(1);

        } else if(!checkedAll.view && !uncheckedAll.view) {

            $scope.privilegeAll.option[0].status = "Checked In Part";
            $scope.privilegeAll.value = "0" + $scope.privilegeAll.value.slice(1);

        }

        if(checkedAll.insert && !uncheckedAll.insert) {

            $scope.privilegeAll.option[1].status = "Checked All";
            $scope.privilegeAll.value = $scope.privilegeAll.value.slice(0, 1) + "7" + $scope.privilegeAll.value.slice(2);

        } else if(!checkedAll.insert && uncheckedAll.insert) {

            $scope.privilegeAll.option[1].status = "Unchecked All";
            $scope.privilegeAll.value = $scope.privilegeAll.value.slice(0, 1) + "0" + $scope.privilegeAll.value.slice(2);

        } else if(!checkedAll.insert && !uncheckedAll.insert) {

            $scope.privilegeAll.option[1].status = "Checked In Part";
            $scope.privilegeAll.value = $scope.privilegeAll.value.slice(0, 1) + "0" + $scope.privilegeAll.value.slice(2);

        }

        if(checkedAll.edit && !uncheckedAll.edit) {

            $scope.privilegeAll.option[2].status = "Checked All";
            $scope.privilegeAll.value = $scope.privilegeAll.value.slice(0, 2) + "7" + $scope.privilegeAll.value.slice(3);

        } else if(!checkedAll.edit && uncheckedAll.edit) {

            $scope.privilegeAll.option[2].status = "Unchecked All";
            $scope.privilegeAll.value = $scope.privilegeAll.value.slice(0, 2) + "0" + $scope.privilegeAll.value.slice(3);

        } else if(!checkedAll.edit && !uncheckedAll.edit) {

            $scope.privilegeAll.option[2].status = "Checked In Part";
            $scope.privilegeAll.value = $scope.privilegeAll.value.slice(0, 2) + "0" + $scope.privilegeAll.value.slice(3);

        }

        if(checkedAll.delete && !uncheckedAll.delete) {

            $scope.privilegeAll.option[3].status = "Checked All";
            $scope.privilegeAll.value = $scope.privilegeAll.value.slice(0, 3) + "7";

        } else if(!checkedAll.delete && uncheckedAll.delete) {

            $scope.privilegeAll.option[3].status = "Unchecked All";
            $scope.privilegeAll.value = $scope.privilegeAll.value.slice(0, 3) + "0";

        } else if(!checkedAll.delete && !uncheckedAll.delete) {

            $scope.privilegeAll.option[3].status = "Checked In Part";
            $scope.privilegeAll.value = $scope.privilegeAll.value.slice(0, 3) + "0";

        }

    }


    $scope.logout = function() {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/login/logout"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.reload();

            }

            $scope.loading.view = false;

        });

    }


    $scope.privilegeAllToggleAllCheckbox = function(privilege) {

        var checkedAll = true;

        angular.forEach($scope.privilege, function(value, key) {

            if(value.value != "7777") {

                checkedAll = false;

                return false;

            }

        });

        if(checkedAll) {

            $scope.privilegeAll.status = "Unchecked All";
            $scope.privilegeAll.value = "0000";

        } else {

            $scope.privilegeAll.status = "Checked All";
            $scope.privilegeAll.value = "7777";

        }

        angular.forEach($scope.privilegeAll.option, function(value, key) {

            if(checkedAll) {

                $scope.privilegeAll.option[key].status = "Unchecked All";

            } else {

                $scope.privilegeAll.option[key].status = "Checked All";

            }

        });

        angular.forEach($scope.privilege, function(value, key) {

            if(checkedAll) {

                $scope.privilege[key].status = "Unchecked All";
                $scope.privilege[key].value = "0000";

            } else {

                $scope.privilege[key].status = "Checked All";
                $scope.privilege[key].value = "7777";

            }

        });

    }


    $scope.privilegeAllToggleCheckbox = function(privilegeItem, selected) {

        var status = "0";
        var index = 0;

        if(privilegeItem.name == "View") {

            status = selected.slice(0, 1);
            index = 0;

        } else if(privilegeItem.name == "Insert") {

            status = selected.slice(1, 2);
            index = 1;

        } else if(privilegeItem.name == "Edit") {

            status = selected.slice(2, 3);
            index = 2;

        } else if(privilegeItem.name == "Delete") {

            status = selected.slice(3, 4);
            index = 3;

        }

        if(status == "0") {

            status = "7";

        } else {

            status = "0";

        }

        if(privilegeItem.name == "View") {

            selected = status + selected.slice(1);

        } else if(privilegeItem.name == "Insert") {

            selected = selected.slice(0, 1) + status + selected.slice(2);

        } else if(privilegeItem.name == "Edit") {

            selected = selected.slice(0, 2) + status + selected.slice(3);

        } else if(privilegeItem.name == "Delete") {

            selected = selected.slice(0, 3) + status;

        }

        $scope.privilegeAll.value = selected;

        if(status == "7") {

            $scope.privilegeAll.option[index].status = "Checked All"

        } else {

            $scope.privilegeAll.option[index].status = "Unchecked All";

        }

        if(selected == "0000") {

            $scope.privilegeAll.status = "Unchecked All";

        } else if(selected == "7777") {

            $scope.privilegeAll.status = "Checked All";

        } else {

            $scope.privilegeAll.status = "Checked In Part";

        }

        angular.forEach($scope.privilege, function(value, key) {

            if(privilegeItem.name == "View") {

                $scope.privilege[key].value = status + value.value.slice(1);

            } else if(privilegeItem.name == "Insert") {

                $scope.privilege[key].value = value.value.slice(0, 1) + status + value.value.slice(2);

            } else if(privilegeItem.name == "Edit") {

                $scope.privilege[key].value = value.value.slice(0, 2) + status + value.value.slice(3);

            } else if(privilegeItem.name == "Delete") {

                $scope.privilege[key].value = value.value.slice(0, 3) + status;

            }

            if($scope.privilege[key].value == "0000") {

                $scope.privilege[key].status = "Unchecked All";

            } else if($scope.privilege[key].value == "7777") {

                $scope.privilege[key].status = "Checked All";

            } else {

                $scope.privilege[key].status = "Checked In Part";

            }

        });

    }


    $scope.privilegeToggleAllCheckbox = function(privilegeName, selected) {

        var checkedAll = {
            "all": true,
            "view": true,
            "insert": true,
            "edit": true,
            "delete": true
        };
        var uncheckedAll = {
            "all": true,
            "view": true,
            "insert": true,
            "edit": true,
            "delete": true
        };

        angular.forEach($scope.privilege, function(value, key) {

            if(value.name == privilegeName) {

                if(selected == "7777") {

                    $scope.privilege[key].status = "Unchecked All";
                    $scope.privilege[key].value = "0000";

                } else {

                    $scope.privilege[key].status = "Checked All";
                    $scope.privilege[key].value = "7777";

                }

            }

            if(value.value != "7777") {

                checkedAll.all = false;

            }

            if(value.value != "0000") {

                uncheckedAll.all = false;

            }

            if(value.value.slice(0, 1) != "7") {

                checkedAll.view = false;

            }

            if(value.value.slice(0, 1) != "0") {

                uncheckedAll.view = false;

            }

            if(value.value.slice(1, 2) != "7") {

                checkedAll.insert = false;

            }

            if(value.value.slice(1, 2) != "0") {

                uncheckedAll.insert = false;

            }

            if(value.value.slice(2, 3) != "7") {

                checkedAll.edit = false;

            }

            if(value.value.slice(2, 3) != "0") {

                uncheckedAll.edit = false;

            }

            if(value.value.slice(3, 4) != "7") {

                checkedAll.delete = false;

            }

            if(value.value.slice(3, 4) != "0") {

                uncheckedAll.delete = false;

            }

        });

        if(checkedAll.all && !uncheckedAll.all) {

            $scope.privilegeAll.status = "Checked All";
            $scope.privilegeAll.value = "7777";

        } else if(!checkedAll.all && uncheckedAll.all) {

            $scope.privilegeAll.status = "Unchecked All";
            $scope.privilegeAll.value = "0000";

        } else if(!checkedAll.all && !uncheckedAll.all) {

            $scope.privilegeAll.status = "Checked In Part";

        }

        if(checkedAll.view && !uncheckedAll.view) {

            $scope.privilegeAll.option[0].status = "Checked All";
            $scope.privilegeAll.value = "7777";

        } else if(!checkedAll.view && uncheckedAll.view) {

            $scope.privilegeAll.option[0].status = "Unchecked All";
            $scope.privilegeAll.value = "0000";

        } else if(!checkedAll.view && !uncheckedAll.view) {

            $scope.privilegeAll.option[0].status = "Checked In Part";

        }

        if(checkedAll.insert && !uncheckedAll.insert) {

            $scope.privilegeAll.option[1].status = "Checked All";
            $scope.privilegeAll.value = "7777";

        } else if(!checkedAll.insert && uncheckedAll.insert) {

            $scope.privilegeAll.option[1].status = "Unchecked All";
            $scope.privilegeAll.value = "0000";

        } else if(!checkedAll.insert && !uncheckedAll.insert) {

            $scope.privilegeAll.option[1].status = "Checked In Part";

        }

        if(checkedAll.edit && !uncheckedAll.edit) {

            $scope.privilegeAll.option[2].status = "Checked All";
            $scope.privilegeAll.value = "7777";

        } else if(!checkedAll.edit && uncheckedAll.edit) {

            $scope.privilegeAll.option[2].status = "Unchecked All";
            $scope.privilegeAll.value = "0000";

        } else if(!checkedAll.edit && !uncheckedAll.edit) {

            $scope.privilegeAll.option[2].status = "Checked In Part";

        }

        if(checkedAll.delete && !uncheckedAll.delete) {

            $scope.privilegeAll.option[3].status = "Checked All";
            $scope.privilegeAll.value = "7777";

        } else if(!checkedAll.delete && uncheckedAll.delete) {

            $scope.privilegeAll.option[3].status = "Unchecked All";
            $scope.privilegeAll.value = "0000";

        } else if(!checkedAll.delete && !uncheckedAll.delete) {

            $scope.privilegeAll.option[3].status = "Checked In Part";

        }

    }


    $scope.privilegeToggleCheckbox = function(privilegeName, privilegeItem, selected) {

        var status = "0";
        var index = 0;

        if(privilegeItem.name == "View") {

            status = selected.slice(0, 1);
            index = 0;

        } else if(privilegeItem.name == "Insert") {

            status = selected.slice(1, 2);
            index = 1;

        } else if(privilegeItem.name == "Edit") {

            status = selected.slice(2, 3);
            index = 2;

        } else if(privilegeItem.name == "Delete") {

            status = selected.slice(3, 4);
            index = 3;

        }

        if(status == "0") {

            status = "7";

        } else {

            status = "0";

        }

        if(privilegeItem.name == "View") {

            selected = status + selected.slice(1);

        } else if(privilegeItem.name == "Insert") {

            selected = selected.slice(0, 1) + status + selected.slice(2);

        } else if(privilegeItem.name == "Edit") {

            selected = selected.slice(0, 2) + status + selected.slice(3);

        } else if(privilegeItem.name == "Delete") {

            selected = selected.slice(0, 3) + status;

        }

        var checkedAll = {
            "all": true,
            "option": true
        };
        var uncheckedAll = {
            "all": true,
            "option": true
        };
        var i = 0;

        angular.forEach($scope.privilege, function(value, key) {

            if(value.name == privilegeName) {

                $scope.privilege[key].value = selected;

                if(selected == "0000") {

                    $scope.privilege[key].status = "Unchecked All";

                } else if(selected == "7777") {

                    $scope.privilege[key].status = "Checked All";

                } else {

                    $scope.privilege[key].status = "Checked In Part";

                }

            }

            if(value.value != "7777") {

                checkedAll.all = false;

            }

            if(value.value != "0000") {

                uncheckedAll.all = false;

            }

            status = "0";

            if(privilegeItem.name == "View") {

                status = value.value.slice(0, 1);

            } else if(privilegeItem.name == "Insert") {

                status = value.value.slice(1, 2);

            } else if(privilegeItem.name == "Edit") {

                status = value.value.slice(2, 3);

            } else if(privilegeItem.name == "Delete") {

                status = value.value.slice(3, 4);

            }

            if(status != "7") {

                checkedAll.option = false;

            }

            if(status != "0") {

                uncheckedAll.option = false;

            }

            i++;

        });

        if(checkedAll.all && !uncheckedAll.all) {

            $scope.privilegeAll.status = "Checked All";
            $scope.privilegeAll.value = "7777";

        } else if(!checkedAll.all && uncheckedAll.all) {

            $scope.privilegeAll.status = "Unchecked All";
            $scope.privilegeAll.value = "0000";

        } else if(!checkedAll.all && !uncheckedAll.all) {

            $scope.privilegeAll.status = "Checked In Part";

        }

        if(checkedAll.option && !uncheckedAll.option) {

            $scope.privilegeAll.option[index].status = "Checked All";

        } else if(!checkedAll.option && uncheckedAll.option) {

            $scope.privilegeAll.option[index].status = "Unchecked All";

        } else if(!checkedAll.option && !uncheckedAll.option) {

            $scope.privilegeAll.option[index].status = "Checked In Part";

        }

    }


    $scope.rebuild = function() {

        $scope.$broadcast("rebuild:scrollbar");

    }


}]);


app.provider("global", function() {


    this.$get = ["$http", function($http) {


        var global = {};


        global.rest = function(rest, callback) {

            $http({
                "data": rest.data,
                "headers": {"Content-Type": "application/json"},
                "method": "POST",
                "url": rest.url
            }).then(function(response) {

                callback(response.data);

            });

        }


        global.restMultipart = function(rest, callback) {

            $http({
                "data": rest.data,
                "headers": {"Content-Type": undefined, "Process-Data": false},
                "method": "POST",
                "transformRequest": angular.identity,
                "url": rest.url
            }).then(function(response) {

                callback(response.data);

            });

        }


        return global;


    }];


});


app.directive("avatarInput", function($parse) {


    var avatarInput = {};


    avatarInput.link = function($scope, element, attrs) {

        element.on("change", function(event) {

            $parse(attrs.avatarInput).assign($scope, element[0].files);
            $scope.$digest();

            $scope.uploadAvatar();

        })

    }


    return avatarInput;


});


app.directive("databaseInput", function($parse) {


    var databaseInput = {};


    databaseInput.link = function($scope, element, attrs) {

        element.on("change", function(event) {

            $parse(attrs.databaseInput).assign($scope, element[0].files);
            $scope.$digest();

            $scope.importDatabase();

        })

    }


    return databaseInput;


});


app.directive("faviconInput", function($parse) {


    var faviconInput = {};


    faviconInput.link = function($scope, element, attrs) {

        element.on("change", function(event) {

            $parse(attrs.faviconInput).assign($scope, element[0].files);
            $scope.$digest();

            $scope.uploadFavicon();

        })

    }


    return faviconInput;


});


app.directive("footerLogoInput", function($parse) {


    var footerLogoInput = {};


    footerLogoInput.link = function($scope, element, attrs) {

        element.on("change", function(event) {

            $parse(attrs.footerLogoInput).assign($scope, element[0].files);
            $scope.$digest();

            $scope.uploadFooterLogo();

        })

    }


    return footerLogoInput;


});


app.directive("imageListInput", function($parse) {


    var imageListInput = {};


    imageListInput.link = function($scope, element, attrs) {

        element.on("change", function(event) {

            $parse(attrs.imageListInput).assign($scope, element[0].files);
            $scope.$digest();

            $scope.uploadImageList();

        })

    }


    return imageListInput;


});


app.directive("logoInput", function($parse) {


    var logoInput = {};


    logoInput.link = function($scope, element, attrs) {

        element.on("change", function(event) {

            $parse(attrs.logoInput).assign($scope, element[0].files);
            $scope.$digest();

            $scope.uploadLogo();

        })

    }


    return logoInput;


});


app.directive("paymentLogoInput", function($parse) {


    var paymentLogoInput = {};


    paymentLogoInput.link = function($scope, element, attrs) {

        element.on("change", function(event) {

            $parse(attrs.paymentLogoInput).assign($scope, element[0].files);
            $scope.$digest();

            $scope.uploadPaymentLogo();

        })

    }


    return paymentLogoInput;


});


app.directive("qrCodeInput", function($parse) {


    var qrCodeInput = {};


    qrCodeInput.link = function($scope, element, attrs) {

        element.on("change", function(event) {

            $parse(attrs.qrCodeInput).assign($scope, element[0].files);
            $scope.$digest();

            $scope.uploadQrCode();

        })

    }


    return qrCodeInput;


});


app.directive("sidebarLogoInput", function($parse) {


    var sidebarLogoInput = {};


    sidebarLogoInput.link = function($scope, element, attrs) {

        element.on("change", function(event) {

            $parse(attrs.sidebarLogoInput).assign($scope, element[0].files);
            $scope.$digest();

            $scope.uploadSidebarLogo();

        })

    }


    return sidebarLogoInput;


});


app.directive("thumbnailListInput", function($parse) {


    var thumbnailListInput = {};


    thumbnailListInput.link = function($scope, element, attrs) {

        element.on("change", function(event) {

            $parse(attrs.thumbnailListInput).assign($scope, element[0].files);
            $scope.$digest();

            $scope.uploadThumbnailList();

        })

    }


    return thumbnailListInput;


});
