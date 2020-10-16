app.controller("userRole", ["$scope", "$window", "global", function($scope, $window, global) {


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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/role/delete"
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
                    "privilege": {
                        "blog": $scope.privilege.blog.value,
                        "blogCategory": $scope.privilege.blogCategory.value,
                        "blogPost": $scope.privilege.blogPost.value,
                        "blogStar": $scope.privilege.blogStar.value,
                        "company": $scope.privilege.company.value,
                        "companyBranch": $scope.privilege.companyBranch.value,
                        "crm": $scope.privilege.crm.value,
                        "crmDatabase": $scope.privilege.crmDatabase.value,
                        "crmDatabaseSource": $scope.privilege.crmDatabaseSource.value,
                        "crmEmail": $scope.privilege.crmEmail.value,
                        "crmEmailBlast": $scope.privilege.crmEmailBlast.value,
                        "crmGroup": $scope.privilege.crmGroup.value,
                        "crmPhone": $scope.privilege.crmPhone.value,
                        "crmPhoneCall": $scope.privilege.crmPhoneCall.value,
                        "crmWhatsapp": $scope.privilege.crmWhatsapp.value,
                        "crmWhatsappBlast": $scope.privilege.crmWhatsappBlast.value,
                        "cryptocurrency": $scope.privilege.cryptocurrency.value,
                        "cryptocurrencyArbitrage": $scope.privilege.cryptocurrencyArbitrage.value,
                        "cryptocurrencyTriangularArbitrage": $scope.privilege.cryptocurrencyTriangularArbitrage.value,
                        "payment": $scope.privilege.payment.value,
                        "paymentAccount": $scope.privilege.paymentAccount.value,
                        "paymentMethod": $scope.privilege.paymentMethod.value,
                        "setting": $scope.privilege.setting.value,
                        "settingSlider": $scope.privilege.settingSlider.value,
                        "shop": $scope.privilege.shop.value,
                        "shopBrand": $scope.privilege.shopBrand.value,
                        "shopCategory": $scope.privilege.shopCategory.value,
                        "shopProduct": $scope.privilege.shopProduct.value,
                        "thirdParty": $scope.privilege.thirdParty.value,
                        "thirdPartyAccount": $scope.privilege.thirdPartyAccount.value,
                        "thirdPartyProvider": $scope.privilege.thirdPartyProvider.value,
                        "transaction": $scope.privilege.transaction.value,
                        "transactionCart": $scope.privilege.transactionCart.value,
                        "user": $scope.privilege.user.value,
                        "userGroup": $scope.privilege.userGroup.value,
                        "userRole": $scope.privilege.userRole.value
                    },
                    "sequence": input.sequence,
                    "status": $scope.status.selected.value
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/user/role/update"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/role/filter-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/user/role/";

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

            $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/user/role/page-" + $scope.site.page.number + "/";

        }

        event.preventDefault();

    }


    $scope.initializeData = function(id) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/role/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.userRole.id != null) {

                    for(var i = 0; i < response.userRole.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.userRole.company.idList[i]);
                        $scope.company.nameList.value.push(response.userRole.company.nameList[i]);

                    }

                    for(var i = 0; i < response.userRole.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.userRole.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.userRole.company.branch.nameList[i]);

                    }

                    var createdTimestamp = new Date(response.userRole.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);
                    $scope.created.timestamp.view = true;

                    $scope.created.user.id.value = response.userRole.created.user.id;
                    $scope.created.user.username.value = response.userRole.created.user.username;
                    $scope.description.value = response.userRole.description;
                    $scope.id.value = response.userRole.id;

                    var modifiedTimestamp = new Date(response.userRole.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);
                    $scope.modified.timestamp.view = true;

                    $scope.modified.user.id.value = response.userRole.modified.user.id;
                    $scope.modified.user.username.value = response.userRole.modified.user.username;
                    $scope.name.value = response.userRole.name;
                    $scope.sequence.value = library.numeral.initializeSeparator(response.userRole.sequence, true);

                    $scope.initializePrivilege(response.userRole.privilege);

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

                    if(response.userRole.id != null) {

                        if(value == response.userRole.status) {

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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/role/initialize-pagination"
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
                    "privilege": {
                        "blog": $scope.privilege.blog.value,
                        "blogCategory": $scope.privilege.blogCategory.value,
                        "blogPost": $scope.privilege.blogPost.value,
                        "blogStar": $scope.privilege.blogStar.value,
                        "company": $scope.privilege.company.value,
                        "companyBranch": $scope.privilege.companyBranch.value,
                        "crm": $scope.privilege.crm.value,
                        "crmDatabase": $scope.privilege.crmDatabase.value,
                        "crmDatabaseSource": $scope.privilege.crmDatabaseSource.value,
                        "crmEmail": $scope.privilege.crmEmail.value,
                        "crmEmailBlast": $scope.privilege.crmEmailBlast.value,
                        "crmGroup": $scope.privilege.crmGroup.value,
                        "crmPhone": $scope.privilege.crmPhone.value,
                        "crmPhoneCall": $scope.privilege.crmPhoneCall.value,
                        "crmWhatsapp": $scope.privilege.crmWhatsapp.value,
                        "crmWhatsappBlast": $scope.privilege.crmWhatsappBlast.value,
                        "cryptocurrency": $scope.privilege.cryptocurrency.value,
                        "cryptocurrencyArbitrage": $scope.privilege.cryptocurrencyArbitrage.value,
                        "cryptocurrencyTriangularArbitrage": $scope.privilege.cryptocurrencyTriangularArbitrage.value,
                        "payment": $scope.privilege.payment.value,
                        "paymentAccount": $scope.privilege.paymentAccount.value,
                        "paymentMethod": $scope.privilege.paymentMethod.value,
                        "setting": $scope.privilege.setting.value,
                        "settingSlider": $scope.privilege.settingSlider.value,
                        "shop": $scope.privilege.shop.value,
                        "shopBrand": $scope.privilege.shopBrand.value,
                        "shopCategory": $scope.privilege.shopCategory.value,
                        "shopProduct": $scope.privilege.shopProduct.value,
                        "thirdParty": $scope.privilege.thirdParty.value,
                        "thirdPartyAccount": $scope.privilege.thirdPartyAccount.value,
                        "thirdPartyProvider": $scope.privilege.thirdPartyProvider.value,
                        "transaction": $scope.privilege.transaction.value,
                        "transactionCart": $scope.privilege.transactionCart.value,
                        "user": $scope.privilege.user.value,
                        "userGroup": $scope.privilege.userGroup.value,
                        "userRole": $scope.privilege.userRole.value
                    },
                    "sequence": input.sequence,
                    "status": $scope.status.selected.value
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/user/role/insert"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/role/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.userRole.id != null) {

                    for(var i = 0; i < response.userRole.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.userRole.company.idList[i]);
                        $scope.company.nameList.value.push(response.userRole.company.nameList[i]);

                    }

                    for(var i = 0; i < response.userRole.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.userRole.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.userRole.company.branch.nameList[i]);

                    }

                    var createdTimestamp = new Date(response.userRole.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);

                    $scope.created.user.id.value = response.userRole.created.user.id;
                    $scope.created.user.username.value = response.userRole.created.user.username;
                    $scope.description.value = response.userRole.description;
                    $scope.id.value = response.userRole.id;

                    var modifiedTimestamp = new Date(response.userRole.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);

                    $scope.modified.user.id.value = response.userRole.modified.user.id;
                    $scope.modified.user.username.value = response.userRole.modified.user.username;
                    $scope.name.value = response.userRole.name;
                    $scope.sequence.value = library.numeral.initializeSeparator(response.userRole.sequence, true);
                    $scope.status.value = $scope.camelCaseToStandardCase(response.userRole.status);

                    $scope.company.option = [];

                    angular.forEach(response.companyList, function(value, key) {

                        $scope.company.option.push({
                            "id": value.id,
                            "name": value.name
                        });

                    });

                    $scope.company.branch.option = [];

                    angular.forEach(response.companyBranchList, function(value, key) {

                        $scope.company.branch.option.push({
                            "id": value.id,
                            "name": value.name
                        });

                    });

                    $scope.initializePrivilege(response.userRole.privilege);

                }

                $scope.popup.view = true;
                $scope.popup.userRole = true;

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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/role/remove-filter-pagination"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/role/set-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/user/role/";

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


app.directive("userRoleDetail", function() {


    var userRole = {};


    userRole.templateUrl = document.getElementById("global").getAttribute("data-base-url") + "/html/user-role-detail-popup.html";


    return userRole;


});
