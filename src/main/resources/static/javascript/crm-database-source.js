app.controller("crmDatabaseSource", ["$scope", "$window", "global", function($scope, $window, global) {


    $scope.filter = {
        "createdDate": {
            "value": ""
        },
        "id": {
            "value": ""
        },
        "name": {
            "value": ""
        },
        "status": {
            "option": [
                {"name": "Status", "value": "Unknown"}
            ]
        }
    };

    $scope.filter.status.selected = $scope.filter.status.option[0];

    $scope.name = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.sequence = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.valid = Object.assign($scope.valid, {
        "name": false,
        "sequence": false
    });


    $scope.checkData = function() {

        $scope.checkCompany();

        $scope.checkCompanyBranch();

        $scope.checkName();

        $scope.checkSequence();

        $scope.checkStatus();

    }


    $scope.checkName = function() {

        if($scope.name.value.length < 2) {

            $scope.name.response.value = "Please enter more than 2 characters";
            $scope.name.response.class = "error";
            $scope.name.response.view = true;
            $scope.valid.name = false;

        } else {

            $scope.name.response.view = false;
            $scope.valid.name = true;

        }

    }


    $scope.checkSequence = function() {

        if($scope.sequence.value != "") {

            if(!$scope.sequence.value.match(/^[0-9,]+$/)) {

                $scope.sequence.response.value = "Please enter only number";
                $scope.sequence.response.class = "error";
                $scope.sequence.response.view = true;
                $scope.valid.sequence = false;

            } else {

                $scope.sequence.response.view = false;
                $scope.valid.sequence = true;

            }

        } else {

            $scope.sequence.response.view = false;
            $scope.valid.sequence = true;

        }

        $scope.sequence.value = library.numeral.initializeSeparator($scope.sequence.value, false);

    }


    $scope.delete = function(id, event) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/crm/database/source/delete"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $scope.response.class = "success";

                document.getElementsByClassName(id)[0].remove();

            } else {

                $scope.response.class = "error";

            }

            $scope.loading.view = false;

            $scope.response.message = response.response;
            $scope.response.view = true;

            $scope.hideResponse();

        });

        event.preventDefault();

    }


    $scope.edit = function(event) {

        $scope.checkData();

        var valid = true;

        angular.forEach($scope.valid, function(value, key) {

            if(!value) {

                valid = false;

                return false;

            }

        });

        if(valid) {

            $scope.loading.view = true;

            var input = $scope.initializeInput();

            var rest = {
                "data": {
                    "id": $scope.id.value,
                    "company": {
                        "branch": {
                            "idList": $scope.company.branch.idList.value,
                            "nameList": $scope.company.branch.nameList.value
                        },
                        "idList": $scope.company.idList.value,
                        "nameList": $scope.company.nameList.value
                    },
                    "description": $scope.description.value,
                    "name": $scope.name.value,
                    "sequence": input.sequence,
                    "status": $scope.status.selected.value
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/crm/database/source/update"
            };
            global.rest(rest, function(response) {

                if(response.result) {

                    $scope.response.class = "success";

                } else {

                    $scope.response.class = "error";

                }

                $scope.loading.view = false;

                $scope.response.message = response.response;
                $scope.response.view = true;

                $scope.hideResponse();

            });

        } else {

            $scope.response.class = "error";
            $scope.response.message = "Please enter a valid data";
            $scope.response.view = true;

            $scope.hideResponse();

        }

        event.preventDefault();

    }


    $scope.filterPagination = function(event) {

        $scope.loading.view = true;

        var filterCreatedDate = $scope.filter.createdDate.value.split(" to ");

        if(filterCreatedDate.length < 2) {

            filterCreatedDate.push("");

        }

        if(filterCreatedDate[0] != "") {

            filterCreatedDate[0] += " 00:00:00";

        }

        if(filterCreatedDate[1] != "") {

            filterCreatedDate[1] += " 23:59:59";

        }

        var data = {
            "id": ["equal", $scope.filter.id.value],
            "created.timestamp": ["between", "date", filterCreatedDate[0], filterCreatedDate[1]],
            "name": ["like", $scope.filter.name.value],
            "status": ["equal", ""]
        };

        if($scope.filter.status.selected.value != "Unknown") {

            data["status"][1] = $scope.filter.status.selected.value;

        }

        var rest = {
            "data": data,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/crm/database/source/filter-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/user/group/";

            } else {

                $scope.loading.view = false;

                $scope.response.class = "error";
                $scope.response.message = response.response;
                $scope.response.view = true;

                $scope.hideResponse();

            }

        });

        event.preventDefault();

    }


    $scope.goToPage = function(event) {

        if(event.which == 13) {

            $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/user/group/page-" + $scope.site.page.number + "/";

        }

        event.preventDefault();

    }


    $scope.initializeData = function(id) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/crm/database/source/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.crmDatabaseSource.id != null) {

                    for(var i = 0; i < response.crmDatabaseSource.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.crmDatabaseSource.company.idList[i]);
                        $scope.company.nameList.value.push(response.crmDatabaseSource.company.nameList[i]);

                    }

                    for(var i = 0; i < response.crmDatabaseSource.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.crmDatabaseSource.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.crmDatabaseSource.company.branch.nameList[i]);

                    }

                    var createdTimestamp = new Date(response.crmDatabaseSource.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);
                    $scope.created.timestamp.view = true;

                    $scope.created.user.id.value = response.crmDatabaseSource.created.user.id;
                    $scope.created.user.username.value = response.crmDatabaseSource.created.user.username;
                    $scope.description.value = response.crmDatabaseSource.description;
                    $scope.id.value = response.crmDatabaseSource.id;

                    var modifiedTimestamp = new Date(response.crmDatabaseSource.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);
                    $scope.modified.timestamp.view = true;

                    $scope.modified.user.id.value = response.crmDatabaseSource.modified.user.id;
                    $scope.modified.user.username.value = response.crmDatabaseSource.modified.user.username;
                    $scope.name.value = response.crmDatabaseSource.name;
                    $scope.sequence.value = library.numeral.initializeSeparator(response.crmDatabaseSource.sequence, true);

                }

                angular.forEach(response.companyBranchList, function(value, key) {

                    $scope.company.branch.option.push({
                        "id": value.id,
                        "name": value.name
                    });

                });

                angular.forEach(response.companyList, function(value, key) {

                    $scope.company.option.push({
                        "id": value.id,
                        "name": value.name
                    });

                });

                angular.forEach(response.statusList, function(value, key) {

                    $scope.status.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.crmDatabaseSource.id != null) {

                        if(value == response.crmDatabaseSource.status) {

                            $scope.status.selected = $scope.status.option[(key + 1)];

                        }

                    }

                });

                $scope.loading.view = false;

            } else {

                $scope.loading.view = false;

                $scope.response.class = "error";
                $scope.response.message = response.response;
                $scope.response.view = true;

                $scope.hideResponse();

            }

        });

    }


    $scope.initializeInput = function() {

        var result = {
            "sequence": 0
        };

        $scope.sequence.value = library.numeral.initializeSeparator($scope.sequence.value, true);

        result.sequence = $scope.sequence.value.replace(/[^0-9]/g, "");

        return result;

    }


    $scope.initializePagination = function() {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/crm/database/source/initialize-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(Object.entries(response.filter).length > 0) {

                    if(response.filter.created_timestamp.length > 3) {

                        if(response.filter.created_timestamp[2] != "" && response.filter.created_timestamp[3] != "") {

                            var startTimestamp = new Date(response.filter.created_timestamp[2]);
                            var endTimestamp = new Date(response.filter.created_timestamp[3]);
                            $scope.filter.createdDate.value = startTimestamp.getFullYear() + "-" + ("0" + (startTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + startTimestamp.getDate()).slice(-2) + " to " + endTimestamp.getFullYear() + "-" + ("0" + (endTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + endTimestamp.getDate()).slice(-2);

                        }

                    }

                    $scope.filter.id.value = response.filter.id[1];
                    $scope.filter.name.value = response.filter.name[1];

                }

                angular.forEach(response.statusList, function(value, key) {

                    $scope.filter.status.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(Object.entries(response.filter).length > 0) {

                        if(value == response.filter.status[1]) {

                            $scope.filter.status.selected = $scope.filter.status.option[(key + 1)];

                        }

                    }

                });

                $scope.site.page.number = response.page;
                $scope.site.page.size = response.size;

                $scope.loading.view = false;

            } else {

                $scope.loading.view = false;

                $scope.response.class = "error";
                $scope.response.message = response.response;
                $scope.response.view = true;

                $scope.hideResponse();

            }

        });

    }


    $scope.insert = function(event) {

        $scope.checkData();

        var valid = true;

        angular.forEach($scope.valid, function(value, key) {

            if(!value) {

                valid = false;

                return false;

            }

        });

        if(valid) {

            $scope.loading.view = true;

            var input = $scope.initializeInput();

            var rest = {
                "data": {
                    "company": {
                        "branch": {
                            "idList": $scope.company.branch.idList.value,
                            "nameList": $scope.company.branch.nameList.value
                        },
                        "idList": $scope.company.idList.value,
                        "nameList": $scope.company.nameList.value
                    },
                    "description": $scope.description.value,
                    "name": $scope.name.value,
                    "sequence": input.sequence,
                    "status": $scope.status.selected.value
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/crm/database/source/insert"
            };
            global.rest(rest, function(response) {

                if(response.result) {

                    $scope.response.class = "success";

                } else {

                    $scope.response.class = "error";

                }

                $scope.loading.view = false;

                $scope.response.message = response.response;
                $scope.response.view = true;

                $scope.hideResponse();

            });

        } else {

            $scope.response.class = "error";
            $scope.response.message = "Please enter a valid data";
            $scope.response.view = true;

            $scope.hideResponse();

        }

        event.preventDefault();

    }


    $scope.loadDetail = function(id) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/crm/database/source/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.crmDatabaseSource.id != null) {

                    for(var i = 0; i < response.crmDatabaseSource.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.crmDatabaseSource.company.idList[i]);
                        $scope.company.nameList.value.push(response.crmDatabaseSource.company.nameList[i]);

                    }

                    for(var i = 0; i < response.crmDatabaseSource.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.crmDatabaseSource.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.crmDatabaseSource.company.branch.nameList[i]);

                    }

                    var createdTimestamp = new Date(response.crmDatabaseSource.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);

                    $scope.created.user.id.value = response.crmDatabaseSource.created.user.id;
                    $scope.created.user.username.value = response.crmDatabaseSource.created.user.username;
                    $scope.description.value = response.crmDatabaseSource.description;
                    $scope.id.value = response.crmDatabaseSource.id;

                    var modifiedTimestamp = new Date(response.crmDatabaseSource.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);

                    $scope.modified.user.id.value = response.crmDatabaseSource.modified.user.id;
                    $scope.modified.user.username.value = response.crmDatabaseSource.modified.user.username;
                    $scope.name.value = response.crmDatabaseSource.name;
                    $scope.sequence.value = library.numeral.initializeSeparator(response.crmDatabaseSource.sequence, true);
                    $scope.status.value = $scope.camelCaseToStandardCase(response.crmDatabaseSource.status);

                    $scope.company.branch.option = [];

                    angular.forEach(response.companyBranchList, function(value, key) {

                        $scope.company.branch.option.push({
                            "id": value.id,
                            "name": value.name
                        });

                    });

                    $scope.company.option = [];

                    angular.forEach(response.companyList, function(value, key) {

                        $scope.company.option.push({
                            "id": value.id,
                            "name": value.name
                        });

                    });

                }

                $scope.popup.view = true;
                $scope.popup.userGroup = true;

                $scope.rebuild();

                $scope.loading.view = false;

            } else {

                $scope.loading.view = false;

                $scope.response.class = "error";
                $scope.response.message = response.response;
                $scope.response.view = true;

                $scope.hideResponse();

            }

        });

    }


    $scope.removeFilterPagination = function(event) {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/crm/database/source/remove-filter-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.reload();

            } else {

                $scope.loading.view = false;

                $scope.response.class = "error";
                $scope.response.message = response.response;
                $scope.response.view = true;

                $scope.hideResponse();

            }

        });

        event.preventDefault();

    }


    $scope.setPagination = function(event) {

        $scope.loading.view = true;

        var rest = {
            "data": $scope.site.page.size,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/crm/database/source/set-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/user/group/";

            } else {

                $scope.loading.view = false;

                $scope.response.class = "error";
                $scope.response.message = response.response;
                $scope.response.view = true;

                $scope.hideResponse();

            }

        });

        event.preventDefault();

    }


}]);


app.directive("crmDatabaseSourceDetail", function() {


    var crmDatabaseSource = {};


    crmDatabaseSource.templateUrl = document.getElementById("global").getAttribute("data-base-url") + "/html/crm-database-source-detail-popup.html";


    return crmDatabaseSource;


});
