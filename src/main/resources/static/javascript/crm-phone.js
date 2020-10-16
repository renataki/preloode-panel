app.controller("crmPhone", ["$scope", "$window", "global", function($scope, $window, global) {


    $scope.attemptList = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": []
    };

    $scope.city = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.contact = {
        "emailAddress": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "faxNumber": {
            "countryCode": {
                "option": []
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "lineId": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "phoneNumber": {
            "countryCode": {
                "option": []
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "wechatId": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "whatsappNumber": {
            "countryCode": {
                "option": []
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        }
    };

    $scope.country = {
        "option": [
            {"name": "Country", "value": "Unknown"}
        ],
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.country.selected = $scope.country.option[0];

    $scope.filter = {
        "country": {
            "option": [
                {"name": "Country", "value": "Unknown"}
            ]
        },
        "createdDate": {
            "value": ""
        },
        "gender": {
            "option": [
                {"name": "Gender", "value": "Unknown"}
            ]
        },
        "group": {
            "option": [
                {"id": "", "name": "Group"}
            ]
        },
        "id": {
            "value": ""
        },
        "name": {
            "first": {
                "value": ""
            },
            "last": {
                "value": ""
            },
            "middle": {
                "value": ""
            }
        },
        "status": {
            "option": [
                {"name": "Status", "value": "Unknown"}
            ]
        },
        "type": {
            "option": [
                {"name": "Type", "value": "Unknown"}
            ]
        }
    };

    $scope.filter.country.selected = $scope.filter.country.option[0];

    $scope.filter.gender.selected = $scope.filter.gender.option[0];

    $scope.filter.group.selected = $scope.filter.group.option[0];

    $scope.filter.status.selected = $scope.filter.status.option[0];

    $scope.filter.type.selected = $scope.filter.type.option[0];

    $scope.gender = {
        "option": [
            {"name": "Gender", "value": "Unknown"}
        ],
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.gender.selected = $scope.gender.option[0];

    $scope.group = {
        "option": [
            {"id": "0", "name": "Group"}
        ],
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.group.selected = $scope.group.option[0];

    $scope.language = {
        "option": [
            {"name": "Language", "value": "Unknown"}
        ],
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.language.selected = $scope.language.option[0];

    $scope.name = {
        "first": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "last": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "middle": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        }
    };

    $scope.source = {
        "option": [
            {"name": "Source", "value": "Unknown"}
        ],
        "response": {
            "class": "",
            "value": "",
            "view": false
        }
    };

    $scope.source.selected = $scope.source.option[0];

    $scope.state = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.type = {
        "option": [
            {"name": "Type", "value": "Unknown"}
        ],
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.type.selected = $scope.type.option[0];

    $scope.valid = Object.assign($scope.valid, {
        "city": false,
        "contactEmailAddress": false,
        "contactFaxNumber": false,
        "contactLineId": false,
        "contactPhoneNumber": false,
        "contactWechatId": false,
        "contactWhatsappNumber": false,
        "country": false,
        "gender": false,
        "group": false,
        "language": false,
        "nameFirst": false,
        "nameLast": false,
        "nameMiddle": false,
        "source": false,
        "state": false,
        "type": false,
        "zipCode": false
    });

    $scope.zipCode = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };


    $scope.checkCity = function() {

        if($scope.city.value != "") {

            if($scope.city.value.length > 20) {

                $scope.city.response.value = "Please enter maximum 20 characters";
                $scope.city.response.class = "error";
                $scope.city.response.view = true;
                $scope.valid.city = false;

            } else {

                $scope.city.response.view = false;
                $scope.valid.city = true;

            }

        } else {

            $scope.city.response.view = false;
            $scope.valid.city = true;

        }

    }


    $scope.checkContactEmailAddress = function() {

        if($scope.contact.emailAddress.value != "") {

            if(!$scope.contact.emailAddress.value.match(/^([0-9A-Za-z_\-\.]){1,}\@([0-9A-Za-z_\-\.]){1,}\.([A-Za-z]){2,}$/) || $scope.contact.emailAddress.value.length > 50) {

                $scope.contact.emailAddress.response.value = "Please enter valid email address";
                $scope.contact.emailAddress.response.class = "error";
                $scope.contact.emailAddress.response.view = true;
                $scope.valid.contactEmailAddress = false;

            } else {

                $scope.contact.emailAddress.response.view = false;
                $scope.valid.contactEmailAddress = true;

            }

        } else {

            $scope.contact.emailAddress.response.view = false;
            $scope.valid.contactEmailAddress = true;

        }

    }


    $scope.checkContactFaxNumber = function() {

        if($scope.contact.faxNumber.value != "") {

            if($scope.contact.faxNumber.value.startsWith("0")) {

                $scope.contact.faxNumber.response.value = "Please enter without leading zero";
                $scope.contact.faxNumber.response.class = "error";
                $scope.contact.faxNumber.response.view = true;
                $scope.valid.contactFaxNumber = false;

            } else if(!$scope.contact.faxNumber.value.match(/^[0-9]+$/) || $scope.contact.faxNumber.value.length < 9 || $scope.contact.faxNumber.value.length > 14) {

                $scope.contact.faxNumber.response.value = "Please enter valid fax number";
                $scope.contact.faxNumber.response.class = "error";
                $scope.contact.faxNumber.response.view = true;
                $scope.valid.contactFaxNumber = false;

            } else {

                $scope.contact.faxNumber.response.view = false;
                $scope.valid.contactFaxNumber = true;

            }

        } else {

            $scope.contact.faxNumber.response.view = false;
            $scope.valid.contactFaxNumber = true;

        }

    }


    $scope.checkContactLineId = function() {

        if($scope.contact.lineId.value != "") {

            if(/\s/.test($scope.contact.lineId.value) || $scope.contact.lineId.value.length < 3 || $scope.contact.lineId.value.length > 20) {

                $scope.contact.lineId.response.value = "Please enter valid line ID";
                $scope.contact.lineId.response.class = "error";
                $scope.contact.lineId.response.view = true;
                $scope.valid.contactLineId = false;

            } else {

                $scope.contact.lineId.response.view = false;
                $scope.valid.contactLineId = true;

            }

        } else {

            $scope.contact.lineId.response.view = false;
            $scope.valid.contactLineId = true;

        }

    }


    $scope.checkContactPhoneNumber = function() {

        if($scope.contact.phoneNumber.value != "") {

            if(!$scope.contact.phoneNumber.value.match(/^[0-9]+$/) || $scope.contact.phoneNumber.value.length < 9 || $scope.contact.phoneNumber.value.length > 14) {

                $scope.contact.phoneNumber.response.value = "Please enter valid phone number";
                $scope.contact.phoneNumber.response.class = "error";
                $scope.contact.phoneNumber.response.view = true;
                $scope.valid.contactPhoneNumber = false;

            } else {

                $scope.contact.phoneNumber.response.view = false;
                $scope.valid.contactPhoneNumber = true;

            }

        } else {

            $scope.contact.phoneNumber.response.view = false;
            $scope.valid.contactPhoneNumber = true;

        }

    }


    $scope.checkContactWechatId = function() {

        if($scope.contact.wechatId.value != "") {

            if(/\s/.test($scope.contact.wechatId.value) || $scope.contact.wechatId.value.length < 3 || $scope.contact.wechatId.value.length > 20) {

                $scope.contact.wechatId.response.value = "Please enter valid wechat ID";
                $scope.contact.wechatId.response.class = "error";
                $scope.contact.wechatId.response.view = true;
                $scope.valid.contactWechatId = false;

            } else {

                $scope.contact.wechatId.response.view = false;
                $scope.valid.contactWechatId = true;

            }

        } else {

            $scope.contact.wechatId.response.view = false;
            $scope.valid.contactWechatId = true;

        }

    }


    $scope.checkContactWhatsappNumber = function() {

        if($scope.contact.whatsappNumber.value != "") {

            if(!$scope.contact.whatsappNumber.value.match(/^[0-9]+$/) || $scope.contact.whatsappNumber.value.length < 9 || $scope.contact.whatsappNumber.value.length > 14) {

                $scope.contact.whatsappNumber.response.value = "Please enter valid whatsapp number";
                $scope.contact.whatsappNumber.response.class = "error";
                $scope.contact.whatsappNumber.response.view = true;
                $scope.valid.contactWhatsappNumber = false;

            } else {

                $scope.contact.whatsappNumber.response.view = false;
                $scope.valid.contactWhatsappNumber = true;

            }

        } else {

            $scope.contact.whatsappNumber.response.view = false;
            $scope.valid.contactWhatsappNumber = true;

        }

    }


    $scope.checkCountry = function() {

        if($scope.country.selected.value == "Unknown") {

            $scope.country.response.value = "Please select country";
            $scope.country.response.class = "error";
            $scope.country.response.view = true;
            $scope.valid.country = false;

        } else {

            $scope.country.response.view = false;
            $scope.valid.country = true;

        }

    }


    $scope.checkData = function() {

        $scope.checkCity();

        $scope.checkCompany();

        $scope.checkCompanyBranch();

        $scope.checkContactEmailAddress();

        $scope.checkContactFaxNumber();

        $scope.checkContactLineId();

        $scope.checkContactPhoneNumber();

        $scope.checkContactWechatId();

        $scope.checkContactWhatsappNumber();

        $scope.checkCountry();

        $scope.checkGender();

        $scope.checkGroup();

        $scope.checkLanguage();

        $scope.checkNameFirst();

        $scope.checkNameLast();

        $scope.checkNameMiddle();

        $scope.checkSource();

        $scope.checkState();

        $scope.checkStatus();

        $scope.checkType();

        $scope.checkZipCode();

    }


    $scope.checkGender = function() {

        if($scope.gender.selected.value == "Unknown") {

            $scope.gender.response.value = "Please select gender";
            $scope.gender.response.class = "error";
            $scope.gender.response.view = true;
            $scope.valid.gender = false;

        } else {

            $scope.gender.response.view = false;
            $scope.valid.gender = true;

        }

    }


    $scope.checkGroup = function() {

        if($scope.group.selected.id == "") {

            $scope.group.response.value = "Please select group";
            $scope.group.response.class = "error";
            $scope.group.response.view = true;
            $scope.valid.group = false;

        } else {

            $scope.group.response.view = false;
            $scope.valid.group = true;

        }

    }


    $scope.checkLanguage = function() {

        if($scope.language.selected.value == "Unknown") {

            $scope.language.response.value = "Please select language";
            $scope.language.response.class = "error";
            $scope.language.response.view = true;
            $scope.valid.language = false;

        } else {

            $scope.language.response.view = false;
            $scope.valid.language = true;

        }

    }


    $scope.checkNameFirst = function() {

        if($scope.name.first.value.length < 2) {

            $scope.name.first.response.value = "Please enter more than 2 characters";
            $scope.name.first.response.class = "error";
            $scope.name.first.response.view = true;
            $scope.valid.nameFirst = false;

        } else {

            $scope.name.first.response.view = false;
            $scope.valid.nameFirst = true;

        }

    }


    $scope.checkNameLast = function() {

        if($scope.name.last.value != "") {

            if($scope.name.last.value.length < 2) {

                $scope.name.last.response.value = "Please enter more than 2 characters";
                $scope.name.last.response.class = "error";
                $scope.name.last.response.view = true;
                $scope.valid.nameLast = false;

            } else {

                $scope.name.last.response.view = false;
                $scope.valid.nameLast = true;

            }

        } else {

            $scope.name.last.response.view = false;
            $scope.valid.nameLast = true;

        }

    }


    $scope.checkNameMiddle = function() {

        if($scope.name.middle.value != "") {

            if($scope.name.middle.value.length < 2) {

                $scope.name.middle.response.value = "Please enter more than 2 characters";
                $scope.name.middle.response.class = "error";
                $scope.name.middle.response.view = true;
                $scope.valid.nameMiddle = false;

            } else {

                $scope.name.middle.response.view = false;
                $scope.valid.nameMiddle = true;

            }

        } else {

            $scope.name.middle.response.view = false;
            $scope.valid.nameMiddle = true;

        }

    }


    $scope.checkSource = function() {

        if($scope.source.selected.value == "Unknown") {

            $scope.source.response.value = "Please select source";
            $scope.source.response.class = "error";
            $scope.source.response.view = true;
            $scope.valid.source = false;

        } else {

            $scope.source.response.view = false;
            $scope.valid.source = true;

        }

    }


    $scope.checkState = function() {

        if($scope.state.value != "") {

            if($scope.state.value.length < 3 && $scope.state.value.length > 20) {

                $scope.state.response.value = "Please enter between 3 - 20 characters";
                $scope.state.response.class = "error";
                $scope.state.response.view = true;
                $scope.valid.state = false;

            } else {

                $scope.state.response.view = false;
                $scope.valid.state = true;

            }

        } else {

            $scope.state.response.view = false;
            $scope.valid.state = true;

        }

    }


    $scope.checkType = function() {

        if($scope.type.selected.value == "Unknown") {

            $scope.type.response.value = "Please select type";
            $scope.type.response.class = "error";
            $scope.type.response.view = true;
            $scope.valid.type = false;

        } else {

            $scope.type.response.view = false;
            $scope.valid.type = true;

        }

    }


    $scope.checkZipCode = function() {

        if($scope.zipCode.value != "") {

            if(!$scope.zipCode.value.match(/^[0-9-]+$/) || $scope.zipCode.value.length > 7) {

                $scope.zipCode.response.value = "Please enter valid zip code";
                $scope.zipCode.response.class = "error";
                $scope.zipCode.response.view = true;
                $scope.valid.zipCode = false;

            } else {

                $scope.zipCode.response.view = false;
                $scope.valid.zipCode = true;

            }

        } else {

            $scope.zipCode.response.view = false;
            $scope.valid.zipCode = true;

        }

    }


    $scope.delete = function(id, event) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/crm/phone/delete"
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
                    "city": $scope.city.value,
                    "company": {
                        "branch": {
                            "idList": $scope.company.branch.idList.value,
                            "nameList": $scope.company.branch.nameList.value
                        },
                        "idList": $scope.company.idList.value,
                        "nameList": $scope.company.nameList.value
                    },
                    "contact": {
                        "emailAddress": $scope.contact.emailAddress.value,
                        "faxNumber": input.contact.faxNumber,
                        "lineId": $scope.contact.lineId.value,
                        "phoneNumber": input.contact.phoneNumber,
                        "wechatId": $scope.contact.wechatId.value,
                        "whatsappNumber": input.contact.whatsappNumber
                    },
                    "country": $scope.country.selected.value,
                    "gender": $scope.gender.selected.value,
                    "group": {
                        "id": $scope.group.selected.id,
                        "name": $scope.group.selected.name
                    },
                    "language": $scope.language.selected.value,
                    "name": {
                        "first": $scope.name.first.value,
                        "last": $scope.name.last.value,
                        "middle": $scope.name.middle.value
                    },
                    "reference": $scope.reference.value,
                    "source": {
                        "id": $scope.source.selected.id,
                        "name": $scope.source.selected.name
                    },
                    "state": $scope.state.value,
                    "status": $scope.status.selected.value,
                    "streetAddress": $scope.streetAddress.value,
                    "type": $scope.type.selected.value,
                    "zipCode": $scope.zipCode.value
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/crm/database/update"
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
            "status": ["equal", ""],
            "type": ["equal", ""]
        };

        if($scope.filter.status.selected.value != "Unknown") {

            data["status"][1] = $scope.filter.status.selected.value;

        }

        if($scope.filter.type.selected.value != "Unknown") {

            data["type"][1] = $scope.filter.type.selected.value;

        }

        var rest = {
            "data": data,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/crm/database/filter-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/crm/database/";

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


    $scope.forceImportDatabase = function() {

        document.getElementsByClassName("crm-database-file")[0].click();

    }


    $scope.goToPage = function(event) {

        if(event.which == 13) {

            $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/crm/database/page-" + $scope.site.page.number + "/";

        }

        event.preventDefault();

    }


    $scope.importDatabase = function() {

        $scope.loading.view = true;

        var formData = new FormData();

        angular.forEach($scope.files, function(value) {

            formData.append("file", value);

        });

        var rest = {
            "data": formData,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/crm/database/import-database"
        };
        global.restMultipart(rest, function(response) {

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

    }


    $scope.initializeCallData = function(id) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/crm/phone/call/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.crmDatabase.id != null) {

                    $scope.city.value = response.crmDatabase.city;

                    for(var i = 0; i < response.crmDatabase.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.crmDatabase.company.idList[i]);
                        $scope.company.nameList.value.push(response.crmDatabase.company.nameList[i]);

                    }

                    for(var i = 0; i < response.crmDatabase.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.crmDatabase.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.crmDatabase.company.branch.nameList[i]);

                    }

                    var createdTimestamp = new Date(response.crmDatabase.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);
                    $scope.created.timestamp.view = true;

                    $scope.created.user.id.value = response.crmDatabase.created.user.id;
                    $scope.created.user.username.value = response.crmDatabase.created.user.username;
                    $scope.contact.emailAddress.value = response.crmDatabase.contact.emailAddress;
                    $scope.contact.lineId.value = response.crmDatabase.contact.lineId;
                    $scope.contact.wechatId.value = response.crmDatabase.contact.wechatId;
                    $scope.id.value = response.crmDatabase.id;

                    var modifiedTimestamp = new Date(response.crmDatabase.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);
                    $scope.modified.timestamp.view = true;

                    $scope.name.first.value = response.crmDatabase.name.first;
                    $scope.name.last.value = response.crmDatabase.name.last;
                    $scope.name.middle.value = response.crmDatabase.name.middle;
                    $scope.reference.value = response.crmDatabase.reference;
                    $scope.state.value = response.crmDatabase.state;
                    $scope.streetAddress.value = response.crmDatabase.streetAddress;
                    $scope.zipCode.value = response.crmDatabase.zipCode;

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

                angular.forEach(response.countryCodeList, function(value, key) {

                    $scope.contact.faxNumber.countryCode.option.push({
                        "number": value,
                        "value": value
                    });

                    $scope.contact.phoneNumber.countryCode.option.push({
                        "number": value,
                        "value": value
                    });

                    $scope.contact.whatsappNumber.countryCode.option.push({
                        "number": value,
                        "value": value
                    });

                    if(response.crmDatabase.id != null) {

                        if(response.crmDatabase.contact.faxNumber.startsWith(value)) {

                            $scope.contact.faxNumber.countryCode.selected = $scope.contact.faxNumber.countryCode.option[key];
                            $scope.contact.faxNumber.value = response.crmDatabase.contact.faxNumber.replace(value, "");

                        }

                        if(response.crmDatabase.contact.phoneNumber.startsWith(value)) {

                            $scope.contact.phoneNumber.countryCode.selected = $scope.contact.phoneNumber.countryCode.option[key];
                            $scope.contact.phoneNumber.value = response.crmDatabase.contact.phoneNumber.replace(value, "");

                        }

                        if(response.crmDatabase.contact.whatsappNumber.startsWith(value)) {

                            $scope.contact.whatsappNumber.countryCode.selected = $scope.contact.whatsappNumber.countryCode.option[key];
                            $scope.contact.whatsappNumber.value = response.crmDatabase.contact.whatsappNumber.replace(value, "");

                        }

                    } else {

                        $scope.contact.faxNumber.countryCode.selected = $scope.contact.faxNumber.countryCode.option[0];

                        $scope.contact.phoneNumber.countryCode.selected = $scope.contact.phoneNumber.countryCode.option[0];

                        $scope.contact.whatsappNumber.countryCode.selected = $scope.contact.whatsappNumber.countryCode.option[0];

                    }

                });

                angular.forEach(response.countryList, function(value, key) {

                    $scope.country.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.crmDatabase.id != null) {

                        if(value == response.crmDatabase.country) {

                            $scope.country.selected = $scope.country.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.crmDatabaseSourceList, function(value, key) {

                    $scope.source.option.push({
                        "id": value.id,
                        "name": value.name
                    });

                    if(response.crmDatabase.id != null) {

                        if(value.id == response.crmDatabase.source.id) {

                            $scope.source.selected = $scope.source.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.crmDatabaseStatusList, function(value, key) {

                    $scope.status.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.crmDatabase.id != null) {

                        if(value == response.crmDatabase.status) {

                            $scope.status.selected = $scope.status.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.crmDatabaseTypeList, function(value, key) {

                    $scope.type.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.crmDatabase.id != null) {

                        if(value == response.crmDatabase.type) {

                            $scope.type.selected = $scope.type.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.crmGroupList, function(value, key) {

                    $scope.group.option.push({
                        "id": value.id,
                        "name": value.name
                    });

                    if(response.crmDatabase.id != null) {

                        if(value.id == response.crmDatabase.group.id) {

                            $scope.group.selected = $scope.group.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.genderList, function(value, key) {

                    $scope.gender.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.crmDatabase.id != null) {

                        if(value == response.crmDatabase.gender) {

                            $scope.gender.selected = $scope.gender.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.languageList, function(value, key) {

                    $scope.language.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.crmDatabase.id != null) {

                        if(value == response.crmDatabase.language) {

                            $scope.language.selected = $scope.language.option[(key + 1)];

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
            "contact": {
                "faxNumber": "",
                "phoneNumber": "",
                "whatsappNumber": ""
            }
        };

        if($scope.contact.faxNumber.value != "") {

            result.contact.faxNumber = $scope.contact.faxNumber.countryCode.selected.value + $scope.contact.faxNumber.value;

        }

        if($scope.contact.phoneNumber.value != "") {

            result.contact.phoneNumber = $scope.contact.phoneNumber.countryCode.selected.value + $scope.contact.phoneNumber.value;

        }

        if($scope.contact.whatsappNumber.value != "") {

            result.contact.whatsappNumber = $scope.contact.whatsappNumber.countryCode.selected.value + $scope.contact.whatsappNumber.value;

        }

        return result;

    }


    $scope.initializePagination = function() {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/crm/database/initialize-pagination"
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

                angular.forEach(response.crmDatabaseStatusList, function(value, key) {

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

                angular.forEach(response.crmDatabaseTypeList, function(value, key) {

                    $scope.filter.type.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(Object.entries(response.filter).length > 0) {

                        if(value == response.filter.type[1]) {

                            $scope.filter.type.selected = $scope.filter.type.option[(key + 1)];

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
                    "attemptList": $scope.attemptList.value,
                    "city": $scope.city.value,
                    "company": {
                        "branch": {
                            "idList": $scope.company.branch.idList.value,
                            "nameList": $scope.company.branch.nameList.value
                        },
                        "idList": $scope.company.idList.value,
                        "nameList": $scope.company.nameList.value
                    },
                    "contact": {
                        "emailAddress": $scope.contact.emailAddress.value,
                        "faxNumber": input.contact.faxNumber,
                        "lineId": $scope.contact.lineId.value,
                        "phoneNumber": input.contact.phoneNumber,
                        "wechatId": $scope.contact.wechatId.value,
                        "whatsappNumber": input.contact.whatsappNumber
                    },
                    "country": $scope.country.selected.value,
                    "gender": $scope.gender.selected.value,
                    "group": {
                        "id": $scope.group.selected.id,
                        "name": $scope.group.selected.name
                    },
                    "language": $scope.language.selected.value,
                    "name": {
                        "first": $scope.name.first.value,
                        "last": $scope.name.last.value,
                        "middle": $scope.name.middle.value
                    },
                    "reference": $scope.reference.value,
                    "source": {
                        "id": $scope.source.selected.id,
                        "name": $scope.source.selected.name
                    },
                    "state": $scope.state.value,
                    "status": $scope.status.selected.value,
                    "streetAddress": $scope.streetAddress.value,
                    "type": $scope.type.selected.value,
                    "zipCode": $scope.zipCode.value
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/crm/database/insert"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/crm/database/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.paymentMethod.id != null) {

                    var businessHourFridayEnd = new Date(response.paymentMethod.businessHour.friday.end);
                    $scope.businessHour.friday.end.value = ("0" + businessHourFridayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourFridayEnd.getMinutes()).slice(-2);

                    var businessHourFridayStart = new Date(response.paymentMethod.businessHour.friday.start);
                    $scope.businessHour.friday.start.value = ("0" + businessHourFridayStart.getHours()).slice(-2) + ":" + ("0" + businessHourFridayStart.getMinutes()).slice(-2);

                    var businessHourMondayEnd = new Date(response.paymentMethod.businessHour.monday.end);
                    $scope.businessHour.monday.end.value = ("0" + businessHourMondayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourMondayEnd.getMinutes()).slice(-2);

                    var businessHourMondayStart = new Date(response.paymentMethod.businessHour.monday.start);
                    $scope.businessHour.monday.start.value = ("0" + businessHourMondayStart.getHours()).slice(-2) + ":" + ("0" + businessHourMondayStart.getMinutes()).slice(-2);

                    var businessHourSaturdayEnd = new Date(response.paymentMethod.businessHour.saturday.end);
                    $scope.businessHour.saturday.end.value = ("0" + businessHourSaturdayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourSaturdayEnd.getMinutes()).slice(-2);

                    var businessHourSaturdayStart = new Date(response.paymentMethod.businessHour.saturday.start);
                    $scope.businessHour.saturday.start.value = ("0" + businessHourSaturdayStart.getHours()).slice(-2) + ":" + ("0" + businessHourSaturdayStart.getMinutes()).slice(-2);

                    var businessHourSundayEnd = new Date(response.paymentMethod.businessHour.sunday.end);
                    $scope.businessHour.sunday.end.value = ("0" + businessHourSundayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourSundayEnd.getMinutes()).slice(-2);

                    var businessHourSundayStart = new Date(response.paymentMethod.businessHour.sunday.start);
                    $scope.businessHour.sunday.start.value = ("0" + businessHourSundayStart.getHours()).slice(-2) + ":" + ("0" + businessHourSundayStart.getMinutes()).slice(-2);

                    var businessHourThursdayEnd = new Date(response.paymentMethod.businessHour.thursday.end);
                    $scope.businessHour.thursday.end.value = ("0" + businessHourThursdayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourThursdayEnd.getMinutes()).slice(-2);

                    var businessHourThursdayStart = new Date(response.paymentMethod.businessHour.thursday.start);
                    $scope.businessHour.thursday.start.value = ("0" + businessHourThursdayStart.getHours()).slice(-2) + ":" + ("0" + businessHourThursdayStart.getMinutes()).slice(-2);

                    var businessHourTuesdayEnd = new Date(response.paymentMethod.businessHour.tuesday.end);
                    $scope.businessHour.tuesday.end.value = ("0" + businessHourTuesdayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourTuesdayEnd.getMinutes()).slice(-2);

                    var businessHourTuesdayStart = new Date(response.paymentMethod.businessHour.tuesday.start);
                    $scope.businessHour.tuesday.start.value = ("0" + businessHourTuesdayStart.getHours()).slice(-2) + ":" + ("0" + businessHourTuesdayStart.getMinutes()).slice(-2);

                    var businessHourWednesdayEnd = new Date(response.paymentMethod.businessHour.wednesday.end);
                    $scope.businessHour.wednesday.end.value = ("0" + businessHourWednesdayEnd.getHours()).slice(-2) + ":" + ("0" + businessHourWednesdayEnd.getMinutes()).slice(-2);

                    var businessHourWednesdayStart = new Date(response.paymentMethod.businessHour.wednesday.start);
                    $scope.businessHour.wednesday.start.value = ("0" + businessHourWednesdayStart.getHours()).slice(-2) + ":" + ("0" + businessHourWednesdayStart.getMinutes()).slice(-2);

                    for(var i = 0; i < response.paymentMethod.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.paymentMethod.company.idList[i]);
                        $scope.company.nameList.value.push(response.paymentMethod.company.nameList[i]);

                    }

                    for(var i = 0; i < response.paymentMethod.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.paymentMethod.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.paymentMethod.company.branch.nameList[i]);

                    }

                    var createdTimestamp = new Date(response.paymentMethod.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);
                    $scope.created.timestamp.view = true;

                    $scope.created.user.id.value = response.paymentMethod.created.user.id;
                    $scope.created.user.username.value = response.paymentMethod.created.user.username;
                    $scope.description.value = response.paymentMethod.description;
                    $scope.footer.display.value = response.paymentMethod.footer.display;

                    if(response.paymentMethod.footer.logo != "") {

                        $scope.footer.logo.upload.file = document.getElementById("global").getAttribute("data-image-url") + "/payment/method/" + response.paymentMethod.footer.logo;
                        $scope.footer.logo.upload.view = true;

                    }

                    $scope.id.value = response.paymentMethod.id;

                    var modifiedTimestamp = new Date(response.paymentMethod.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);
                    $scope.modified.timestamp.view = true;

                    $scope.modified.user.id.value = response.paymentMethod.modified.user.id;
                    $scope.modified.user.username.value = response.paymentMethod.modified.user.username;
                    $scope.name.value = response.paymentMethod.name;
                    $scope.payment.display.value = response.paymentMethod.payment.display;

                    if(response.paymentMethod.payment.logo != "") {

                        $scope.payment.logo.upload.file = document.getElementById("global").getAttribute("data-image-url") + "/payment/method/" + response.paymentMethod.payment.logo;
                        $scope.payment.logo.upload.view = true;

                    }

                    $scope.sequence.value = library.numeral.initializeSeparator(response.paymentMethod.sequence, true);
                    $scope.sidebar.display.value = response.paymentMethod.sidebar.display;

                    if(response.paymentMethod.sidebar.logo != "") {

                        $scope.sidebar.logo.upload.file = document.getElementById("global").getAttribute("data-image-url") + "/payment/method/" + response.paymentMethod.sidebar.logo;
                        $scope.sidebar.logo.upload.view = true;

                    }

                    $scope.source.value = response.crmDatabaseSource.source.name;
                    $scope.status.value = $scope.camelCaseToStandardCase(response.paymentMethod.status);
                    $scope.type.value = $scope.camelCaseToStandardCase(response.paymentMethod.type);

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

                    $scope.initializeSwitch();

                }

                $scope.popup.view = true;
                $scope.popup.paymentMethod = true;

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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/crm/database/remove-filter-pagination"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/crm/database/set-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/crm/database/";

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


app.directive("crmDatabaseDetail", function() {


    var crmDatabase = {};


    crmDatabase.templateUrl = document.getElementById("global").getAttribute("data-base-url") + "/html/crm-database-detail-popup.html";


    return crmDatabase;


});
