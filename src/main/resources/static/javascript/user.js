app.controller("user", ["$scope", "$window", "global", function($scope, $window, global) {


    $scope.account = {
        "creditList": {
            "value": []
        },
        "index": [],
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "thirdParty": {
            "account": {
                "idList": {
                    "value": []
                },
                "usernameList": {
                    "value": []
                }
            },
            "provider": {
                "idList": {
                    "value": []
                },
                "nameList": {
                    "value": []
                }
            }
        },
        "usernameList": {
            "value": []
        }
    };

    $scope.avatar = {
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

    $scope.credit = {
        "main": {
            "value": 0
        },
        "promotion": {
            "value": 0
        }
    }

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
        "role": {
            "option": [
                {"id": "", "name": "Role"}
            ]
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
        },
        "username": {
            "value": ""
        }
    };

    $scope.filter.country.selected = $scope.filter.country.option[0];

    $scope.filter.gender.selected = $scope.filter.gender.option[0];

    $scope.filter.group.selected = $scope.filter.group.option[0];

    $scope.filter.role.selected = $scope.filter.role.option[0];

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

    $scope.password = {
        "confirm": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        },
        "main": {
            "response": {
                "class": "",
                "value": "",
                "view": false
            },
            "value": ""
        }
    };

    $scope.payment = {
        "branchList": {
            "value": []
        },
        "index": [],
        "method": [],
        "nameList": {
            "value": []
        },
        "numberList": {
            "value": []
        },
        "response": {
            "class": "",
            "value": "",
            "view": false
        }
    };

    $scope.role = {
        "option": [
            {"id": "", "name": "Role"}
        ],
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.role.selected = $scope.role.option[0];

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

    $scope.username = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.valid = Object.assign($scope.valid, {
        "account": false,
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
        "passwordConfirm": false,
        "passwordMain": false,
        "paymentBranch": false,
        "paymentMethod": false,
        "paymentName": false,
        "paymentNumber": false,
        "role": false,
        "state": false,
        "type": false,
        "username": false,
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


    $scope.checkAccount = function() {

        $scope.account.response.view = false;
        $scope.valid.account = true;

    }


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

        $scope.checkAccount();

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

        $scope.checkPasswordConfirm();

        $scope.checkPasswordMain();

        $scope.checkPaymentBranchList();

        $scope.checkPaymentMethod();

        $scope.checkPaymentNameList()

        $scope.checkPaymentNumberList();

        $scope.checkRole();

        $scope.checkState();

        $scope.checkStatus();

        $scope.checkType();

        $scope.checkUsername();

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


    $scope.checkPasswordConfirm = function() {

        if(!$scope.password.confirm.value.match($scope.password.main.value)) {

            $scope.password.confirm.response.value = "Password doesn't match";
            $scope.password.confirm.response.class = "error";
            $scope.password.confirm.response.view = true;
            $scope.valid.passwordConfirm = false;

        } else {

            $scope.password.confirm.response.view = false;
            $scope.valid.passwordConfirm = true;

        }

    }


    $scope.checkPasswordMain = function() {

        if($scope.password.main.value.length < 3) {

            $scope.password.main.response.value = "Please enter more than 2 characters";
            $scope.password.main.response.class = "error";
            $scope.password.main.response.view = true;
            $scope.valid.passwordMain = false;

        } else if(/[a-z]/.test($scope.password.main.value) && /[A-Z]/.test($scope.password.main.value) && /[0-9]/.test($scope.password.main.value) && /[-!$%^&*()_+|~=`{}\[\]:";'<>?,.\/]/.test($scope.password.main.value)) {

            $scope.password.main.response.value = "Strong security password";
            $scope.password.main.response.class = "success";
            $scope.password.main.response.view = true;
            $scope.valid.passwordMain = true;

        } else if(/[A-Z]/.test($scope.password.main.value) && /[0-9]/.test($scope.password.main.value) && /[-!$%^&*()_+|~=`{}\[\]:";'<>?,.\/]/.test($scope.password.main.value)) {

            $scope.password.main.response.value = "Secured password";
            $scope.password.main.response.class = "success";
            $scope.password.main.response.view = true;
            $scope.valid.passwordMain = true;

        } else if(/[a-z]/.test($scope.password.main.value) && /[0-9]/.test($scope.password.main.value) && /[-!$%^&*()_+|~=`{}\[\]:";'<>?,.\/]/.test($scope.password.main.value)) {

            $scope.password.main.response.value = "Secured password";
            $scope.password.main.response.class = "success";
            $scope.password.main.response.view = true;
            $scope.valid.passwordMain = true;

        } else if(/[a-z]/.test($scope.password.main.value) && /[A-Z]/.test($scope.password.main.value) && /[-!$%^&*()_+|~=`{}\[\]:";'<>?,.\/]/.test($scope.password.main.value)) {

            $scope.password.main.response.value = "Secured password";
            $scope.password.main.response.class = "success";
            $scope.password.main.response.view = true;
            $scope.valid.passwordMain = true;

        } else if(/[a-z]/.test($scope.password.main.value) && /[A-Z]/.test($scope.password.main.value) && /[0-9]/.test($scope.password.main.value)) {

            $scope.password.main.response.value = "Secured password";
            $scope.password.main.response.class = "success";
            $scope.password.main.response.view = true;
            $scope.valid.passwordMain = true;

        } else if(/[0-9]/.test($scope.password.main.value) && /[-!$%^&*()_+|~=`{}\[\]:";'<>?,.\/]/.test($scope.password.main.value)) {

            $scope.password.main.response.value = "Medium security password";
            $scope.password.main.response.class = "warning";
            $scope.password.main.response.view = true;
            $scope.valid.passwordMain = true;

        } else if(/[A-Z]/.test($scope.password.main.value) && /[-!$%^&*()_+|~=`{}\[\]:";'<>?,.\/]/.test($scope.password.main.value)) {

            $scope.password.main.response.value = "Medium security password";
            $scope.password.main.response.class = "warning";
            $scope.password.main.response.view = true;
            $scope.valid.passwordMain = true;

        } else if(/[A-Z]/.test($scope.password.main.value) && /[0-9]/.test($scope.password.main.value)) {

            $scope.password.main.response.value = "Medium security password";
            $scope.password.main.response.class = "warning";
            $scope.password.main.response.view = true;
            $scope.valid.passwordMain = true;

        } else if(/[a-z]/.test($scope.password.main.value) && /[-!$%^&*()_+|~=`{}\[\]:";'<>?,.\/]/.test($scope.password.main.value)) {

            $scope.password.main.response.value = "Medium security password";
            $scope.password.main.response.class = "warning";
            $scope.password.main.response.view = true;
            $scope.valid.passwordMain = true;

        } else if(/[a-z]/.test($scope.password.main.value) && /[0-9]/.test($scope.password.main.value)) {

            $scope.password.main.response.value = "Medium security password";
            $scope.password.main.response.class = "warning";
            $scope.password.main.response.view = true;
            $scope.valid.passwordMain = true;

        } else if(/[a-z]/.test($scope.password.main.value) && /[A-Z]/.test($scope.password.main.value)) {

            $scope.password.main.response.value = "Medium security password";
            $scope.password.main.response.class = "warning";
            $scope.password.main.response.view = true;
            $scope.valid.passwordMain = true;

        } else if(/[-!$%^&*()_+|~=`{}\[\]:";'<>?,.\/]/.test($scope.password.main.value)) {

            $scope.password.main.response.value = "Low security password";
            $scope.password.main.response.class = "warning";
            $scope.password.main.response.view = true;
            $scope.valid.passwordMain = true;

        } else if(/[0-9]/.test($scope.password.main.value)) {

            $scope.password.main.response.value = "Low security password";
            $scope.password.main.response.class = "warning";
            $scope.password.main.response.view = true;
            $scope.valid.passwordMain = true;

        } else if(/[A-Z]/.test($scope.password.main.value)) {

            $scope.password.main.response.value = "Low security password";
            $scope.password.main.response.class = "warning";
            $scope.password.main.response.view = true;
            $scope.valid.passwordMain = true;

        } else if(/[a-z]/.test($scope.password.main.value)) {

            $scope.password.main.response.value = "Low security password";
            $scope.password.main.response.class = "warning";
            $scope.password.main.response.view = true;
            $scope.valid.passwordMain = true;

        } else {

            $scope.password.main.response.view = false;
            $scope.valid.passwordMain = true;

        }

    }


    $scope.checkPaymentBranchList = function() {

        if($scope.payment.index.length > 0) {

            for(var i = 0; i < $scope.payment.index.length; i++) {

                if($scope.payment.branchList.value[i] != "") {

                    if($scope.payment.branchList.value[i].length < 2) {

                        $scope.payment.response.value = "Please enter payment branch more than 2 characters";
                        $scope.payment.response.class = "error";
                        $scope.payment.response.view = true;
                        $scope.valid.paymentBranch = false;

                        break;

                    } else {

                        $scope.payment.response.view = false;
                        $scope.valid.paymentBranch = true;

                    }

                } else {

                    $scope.payment.response.view = false;
                    $scope.valid.paymentBranch = true;

                }

            }

        } else {

            $scope.payment.response.view = false;
            $scope.valid.paymentBranch = true;

        }

    }


    $scope.checkPaymentMethod = function() {

        if($scope.payment.index.length > 0) {

            for(var i = 0; i < $scope.payment.index.length; i++) {

                if($scope.payment.method[i].selected.id == "") {

                    $scope.payment.response.value = "Please select payment method";
                    $scope.payment.response.class = "error";
                    $scope.payment.response.view = true;
                    $scope.valid.paymentMethod = false;

                    break;

                } else {

                    $scope.payment.response.view = false;
                    $scope.valid.paymentMethod = true;

                }

            }

        } else {

            $scope.payment.response.view = false;
            $scope.valid.paymentMethod = true;

        }

    }


    $scope.checkPaymentNameList = function() {

        if($scope.payment.index.length > 0) {

            for(var i = 0; i < $scope.payment.index.length; i++) {

                if($scope.payment.nameList.value[i].length < 2) {

                    $scope.payment.response.value = "Please enter payment name more than 2 characters";
                    $scope.payment.response.class = "error";
                    $scope.payment.response.view = true;
                    $scope.valid.paymentName = false;

                    break;

                } else {

                    $scope.payment.response.view = false;
                    $scope.valid.paymentName = true;

                }

            }

        } else {

            $scope.payment.response.view = false;
            $scope.valid.paymentName = true;

        }

    }


    $scope.checkPaymentNumberList = function() {

        if($scope.payment.index.length > 0) {

            for(var i = 0; i < $scope.payment.index.length; i++) {

                if(!$scope.payment.numberList.value[i].match(/^[0-9]+$/)) {

                    $scope.payment.response.value = "Please enter payment number only number";
                    $scope.payment.response.class = "error";
                    $scope.payment.response.view = true;
                    $scope.valid.paymentNumber = false;

                    break;

                } else {

                    $scope.payment.response.view = false;
                    $scope.valid.paymentNumber = true;

                }

            }

        } else {

            $scope.payment.response.view = false;
            $scope.valid.paymentNumber = true;

        }

    }


    $scope.checkRole = function() {

        if($scope.role.selected.id == "") {

            $scope.role.response.value = "Please select role";
            $scope.role.response.class = "error";
            $scope.role.response.view = true;
            $scope.valid.role = false;

        } else {

            var rest = {
                "data": $scope.role.selected.id,
                "url": document.getElementById("global").getAttribute("data-base-url") + "/user/initialize-role"
            };
            global.rest(rest, function(response) {

                if(response.result) {

                    if(response.userRole.id != null) {

                        $scope.initializePrivilege(response.userRole.privilege);

                    }

                }

            });

            $scope.role.response.view = false;
            $scope.valid.role = true;

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


    $scope.checkUsername = function() {

        if(/\s/.test($scope.username.value)) {

            $scope.username.response.value = "Please enter without whitespace";
            $scope.username.response.class = "error";
            $scope.username.response.view = true;
            $scope.valid.username = false;

        } else if($scope.username.value.length < 3 || $scope.username.value.length > 20) {

            $scope.username.response.value = "Please enter between 3 - 20 characters";
            $scope.username.response.class = "error";
            $scope.username.response.view = true;
            $scope.valid.username = false;

        } else {

            $scope.username.response.view = false;
            $scope.valid.username = true;

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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/delete"
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


    $scope.deletePayment = function(key, event) {

        $scope.payment.index.splice(key, 1);
        $scope.payment.branchList.value.splice(key, 1);
        $scope.payment.method.idList.value.splice(key, 1);
        $scope.payment.method.nameList.value.splice(key, 1);
        $scope.payment.method.typeList.value.splice(key, 1);
        $scope.payment.nameList.value.splice(key, 1);
        $scope.payment.numberList.value.splice(key, 1);

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
                    "account": {
                        "thirdParty": {
                            "account": {
                                "idList": $scope.account.thirdParty.account.idList.value,
                                "usernameList": $scope.account.thirdParty.account.usernameList.value
                            },
                            "provider": {
                                "idList": $scope.account.thirdParty.provider.idList.value,
                                "nameList": $scope.account.thirdParty.provider.nameList.value
                            }
                        },
                        "usernameList": $scope.account.usernameList.value
                    },
                    "avatar": $scope.avatar.value,
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
                    "password": {
                        "main": input.password.main,
                        "recovery": input.password.recovery
                    },
                    "payment": {
                        "branchList": $scope.payment.branchList.value,
                        "method": {
                            "idList": input.payment.method.idList,
                            "nameList": input.payment.method.nameList,
                            "typeList": input.payment.method.typeList
                        },
                        "nameList": $scope.payment.nameList.value,
                        "numberList": $scope.payment.numberList.value
                    },
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
                    "reference": $scope.reference.value,
                    "role": {
                        "id": $scope.role.selected.id,
                        "name": $scope.role.selected.name
                    },
                    "state": $scope.state.value,
                    "status": $scope.status.selected.value,
                    "streetAddress": $scope.streetAddress.value,
                    "type": $scope.type.selected.value,
                    "username": $scope.username.value,
                    "zipCode": $scope.zipCode.value
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/user/update"
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
            "country": ["equal", ""],
            "created.timestamp": ["between", "date", filterCreatedDate[0], filterCreatedDate[1]],
            "gender": ["equal", ""],
            "group.id": ["equal", $scope.filter.group.selected.id],
            "name.first": ["like", $scope.filter.name.first.value],
            "name.last": ["like", $scope.filter.name.last.value],
            "name.middle": ["like", $scope.filter.name.middle.value],
            "role.id": ["equal", $scope.filter.role.selected.id],
            "status": ["equal", ""],
            "type": ["equal", ""],
            "username": ["like", $scope.filter.username.value]
        };

        if($scope.filter.country.selected.value != "Unknown") {

            data["country"][1] = $scope.filter.country.selected.value;

        }

        if($scope.filter.gender.selected.value != "Unknown") {

            data["gender"][1] = $scope.filter.gender.selected.value;

        }

        if($scope.filter.status.selected.value != "Unknown") {

            data["status"][1] = $scope.filter.status.selected.value;

        }

        if($scope.filter.type.selected.value != "Unknown") {

            data["type"][1] = $scope.filter.type.selected.value;

        }

        var rest = {
            "data": data,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/filter-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/user/";

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


    $scope.forceUploadAvatar = function() {

        document.getElementsByClassName("user-avatar")[0].click();

    }


    $scope.goToPage = function(event) {

        if(event.which == 13) {

            $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/user/page-" + $scope.site.page.number + "/";

        }

        event.preventDefault();

    }


    $scope.initializeData = function(id) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.user.id != null) {

                    if(response.user.avatar != "") {

                        $scope.avatar.value = response.user.avatar;
                        $scope.avatar.upload.file = response.user.avatar;
                        $scope.avatar.upload.view = true;

                    }

                    $scope.city.value = response.user.city;

                    for(var i = 0; i < response.user.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.user.company.idList[i]);
                        $scope.company.nameList.value.push(response.user.company.nameList[i]);

                    }

                    for(var i = 0; i < response.user.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.user.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.user.company.branch.nameList[i]);

                    }

                    var createdTimestamp = new Date(response.user.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);
                    $scope.created.timestamp.view = true;

                    $scope.created.user.id.value = response.user.created.user.id;
                    $scope.created.user.username.value = response.user.created.user.username;
                    $scope.credit.main.value = library.numeral.initializeSeparator(response.user.credit.main, true);
                    $scope.credit.promotion.value = library.numeral.initializeSeparator(response.user.credit.promotion, true);
                    $scope.contact.emailAddress.value = response.user.contact.emailAddress;
                    $scope.contact.lineId.value = response.user.contact.lineId;
                    $scope.contact.wechatId.value = response.user.contact.wechatId;
                    $scope.id.value = response.user.id;

                    var modifiedTimestamp = new Date(response.user.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);
                    $scope.modified.timestamp.view = true;

                    $scope.name.first.value = response.user.name.first;
                    $scope.name.last.value = response.user.name.last;
                    $scope.name.middle.value = response.user.name.middle;
                    $scope.password.confirm.value = library.encryption.rsaDecrypt(response.user.password.main);
                    $scope.password.main.value = library.encryption.rsaDecrypt(response.user.password.main);

                    for(var i = 0; i < response.user.payment.nameList.length; i++) {

                        $scope.payment.index.push(i);
                        $scope.payment.branchList.value.push(response.user.payment.branchList[i]);

                        $scope.payment.method.push({
                            "option": [{
                                "id": "",
                                "name": "Method",
                                "type": ""
                            }],
                            "value": ""
                        });

                        angular.forEach(response.paymentMethodList, function(value, key) {

                            $scope.payment.method[i].option.push({
                                "id": value.id,
                                "name": value.name,
                                "type": value.type
                            });

                            if(value.id == response.user.payment.method.idList[i]) {

                                $scope.payment.method[i].selected = $scope.payment.method[i].option[(key + 1)];

                            }

                        });

                        $scope.payment.nameList.value.push(response.user.payment.nameList[i]);
                        $scope.payment.numberList.value.push(response.user.payment.numberList[i]);

                    }

                    $scope.reference.value = response.user.reference;
                    $scope.state.value = response.user.state;
                    $scope.streetAddress.value = response.user.streetAddress;
                    $scope.username.value = response.user.username;
                    $scope.zipCode.value = response.user.zipCode;

                    $scope.initializePrivilege(response.user.privilege);

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

                    if(response.user.id != null) {

                        if(response.user.contact.faxNumber.startsWith(value)) {

                            $scope.contact.faxNumber.countryCode.selected = $scope.contact.faxNumber.countryCode.option[key];
                            $scope.contact.faxNumber.value = response.user.contact.faxNumber.replace(value, "");

                        }

                        if(response.user.contact.phoneNumber.startsWith(value)) {

                            $scope.contact.phoneNumber.countryCode.selected = $scope.contact.phoneNumber.countryCode.option[key];
                            $scope.contact.phoneNumber.value = response.user.contact.phoneNumber.replace(value, "");

                        }

                        if(response.user.contact.whatsappNumber.startsWith(value)) {

                            $scope.contact.whatsappNumber.countryCode.selected = $scope.contact.whatsappNumber.countryCode.option[key];
                            $scope.contact.whatsappNumber.value = response.user.contact.whatsappNumber.replace(value, "");

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

                    if(response.user.id != null) {

                        if(value == response.user.country) {

                            $scope.country.selected = $scope.country.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.genderList, function(value, key) {

                    $scope.gender.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.user.id != null) {

                        if(value == response.user.gender) {

                            $scope.gender.selected = $scope.gender.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.languageList, function(value, key) {

                    $scope.language.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.user.id != null) {

                        if(value == response.user.language) {

                            $scope.language.selected = $scope.language.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.thirdPartyProviderList, function(value, key) {

                    angular.forEach(response.thirdPartyAccountList, function(valueChild1, key) {

                        if(valueChild1.provider.id == value.id) {

                            $scope.account.index.push($scope.account.index.length);

                            if(response.user.id != null) {

                                for(var i = 0; i < response.user.account.usernameList.length; i++) {

                                    if(response.user.account.thirdParty.provider.idList[i] == value.id && response.user.account.thirdParty.account.idList[i] == valueChild1.id) {

                                        $scope.account.creditList.value.push(library.numeral.initializeSeparator(response.user.account.creditList[i], true));
                                        $scope.account.usernameList.value.push(response.user.account.usernameList[i]);

                                    }

                                }

                                if($scope.account.creditList.value.length < $scope.account.index.length || $scope.account.usernameList.value.length < $scope.account.index.length) {

                                    $scope.account.creditList.value.push(library.numeral.initializeSeparator(0, true));
                                    $scope.account.usernameList.value.push("");

                                }

                            } else {

                                $scope.account.creditList.value.push(library.numeral.initializeSeparator(0, true));
                                $scope.account.usernameList.value.push("");

                            }

                            $scope.account.thirdParty.account.idList.value.push(valueChild1.id);
                            $scope.account.thirdParty.account.usernameList.value.push(valueChild1.username);
                            $scope.account.thirdParty.provider.idList.value.push(value.id);
                            $scope.account.thirdParty.provider.nameList.value.push(value.name);

                        }

                    });

                });

                angular.forEach(response.userGroupList, function(value, key) {

                    $scope.group.option.push({
                        "id": value.id,
                        "name": value.name
                    });

                    if(response.user.id != null) {

                        if(value.id == response.user.group.id) {

                            $scope.group.selected = $scope.group.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.userRoleList, function(value, key) {

                    $scope.role.option.push({
                        "id": value.id,
                        "name": value.name
                    });

                    if(response.user.id != null) {

                        if(value.id == response.user.role.id) {

                            $scope.role.selected = $scope.role.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.userStatusList, function(value, key) {

                    $scope.status.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.user.id != null) {

                        if(value == response.user.status) {

                            $scope.status.selected = $scope.status.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.userTypeList, function(value, key) {

                    $scope.type.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.user.id != null) {

                        if(value == response.user.type) {

                            $scope.type.selected = $scope.type.option[(key + 1)];

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
            },
            "password": {
                "main": "",
                "recovery": ""
            },
            "payment": {
                "method": {
                    "idList": [],
                    "nameList": [],
                    "typeList": []
                }
            }
        };

        if($scope.password.main.value != "") {

            result.password.main = library.encryption.rsaEncrypt($scope.password.main.value);
            result.password.recovery = result.password.main;

        }

        if($scope.contact.faxNumber.value != "") {

            result.contact.faxNumber = $scope.contact.faxNumber.countryCode.selected.value + $scope.contact.faxNumber.value;

        }

        if($scope.contact.phoneNumber.value != "") {

            result.contact.phoneNumber = $scope.contact.phoneNumber.countryCode.selected.value + $scope.contact.phoneNumber.value;

        }

        if($scope.contact.whatsappNumber.value != "") {

            result.contact.whatsappNumber = $scope.contact.whatsappNumber.countryCode.selected.value + $scope.contact.whatsappNumber.value;

        }

        angular.forEach($scope.payment.method, function(value, key) {

            result.payment.method.idList.push(value.selected.id);
            result.payment.method.nameList.push(value.selected.name);
            result.payment.method.typeList.push(value.selected.type);

        });

        return result;

    }


    $scope.initializePagination = function() {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/initialize-pagination"
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
                    $scope.filter.name.first.value = response.filter.name_first[1];
                    $scope.filter.name.last.value = response.filter.name_last[1];
                    $scope.filter.name.middle.value = response.filter.name_middle[1];
                    $scope.filter.username.value = response.filter.username[1];

                }

                angular.forEach(response.countryList, function(value, key) {

                    $scope.filter.country.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(Object.entries(response.filter).length > 0) {

                        if(value == response.filter.country[1]) {

                            $scope.filter.country.selected = $scope.filter.country.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.genderList, function(value, key) {

                    $scope.filter.gender.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(Object.entries(response.filter).length > 0) {

                        if(value == response.filter.gender[1]) {

                            $scope.filter.gender.selected = $scope.filter.gender.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.userGroupList, function(value, key) {

                    $scope.filter.group.option.push({
                        "id": value.id,
                        "name": value.name
                    });

                    if(Object.entries(response.filter).length > 0) {

                        if(value.id == response.filter.group_id[1]) {

                            $scope.filter.group.selected = $scope.filter.group.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.userRoleList, function(value, key) {

                    $scope.filter.role.option.push({
                        "id": value.id,
                        "name": value.name
                    });

                    if(Object.entries(response.filter).length > 0) {

                        if(value.id == response.filter.role_id[1]) {

                            $scope.filter.role.selected = $scope.filter.role.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.userStatusList, function(value, key) {

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

                angular.forEach(response.userTypeList, function(value, key) {

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
                    "account": {
                        "thirdParty": {
                            "account": {
                                "idList": $scope.account.thirdParty.account.idList.value,
                                "usernameList": $scope.account.thirdParty.account.usernameList.value
                            },
                            "provider": {
                                "idList": $scope.account.thirdParty.provider.idList.value,
                                "nameList": $scope.account.thirdParty.provider.nameList.value
                            }
                        },
                        "usernameList": $scope.account.usernameList.value
                    },
                    "avatar": $scope.avatar.value,
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
                    "password": {
                        "main": input.password.main,
                        "recovery": input.password.recovery
                    },
                    "payment": {
                        "branchList": $scope.payment.branchList.value,
                        "method": {
                            "idList": input.payment.method.idList,
                            "nameList": input.payment.method.nameList,
                            "typeList": input.payment.method.typeList
                        },
                        "nameList": $scope.payment.nameList.value,
                        "numberList": $scope.payment.numberList.value
                    },
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
                    "reference": $scope.reference.value,
                    "role": {
                        "id": $scope.role.selected.id,
                        "name": $scope.role.selected.name
                    },
                    "state": $scope.state.value,
                    "status": $scope.status.selected.value,
                    "streetAddress": $scope.streetAddress.value,
                    "type": $scope.type.selected.value,
                    "username": $scope.username.value,
                    "zipCode": $scope.zipCode.value
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/user/insert"
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


    $scope.insertPayment = function(event) {

        var i = $scope.payment.index.length;
        $scope.payment.index.push(i);
        $scope.payment.branchList.value.push("");
        $scope.payment.method.push({});
        $scope.payment.nameList.value.push("");
        $scope.payment.numberList.value.push("");

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/initialize-payment-method"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $scope.payment.method[i] = {
                    "option": [{
                        "id": "",
                        "name": "Method",
                        "type": ""
                    }],
                    "value": ""
                };

                $scope.payment.method[i].selected = $scope.payment.method[i].option[0];

                angular.forEach(response.paymentMethodList, function(value, key) {

                    $scope.payment.method[i].option.push({
                        "id": value.id,
                        "name": value.name,
                        "type": value.type
                    });

                });

            }

            $scope.loading.view = false;

        });

        event.preventDefault();

    }


    $scope.loadDetail = function(id) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.user.id != null) {

                    for(var i = 0; i < response.user.account.usernameList.length; i++) {

                        $scope.account.creditList.value.push(library.numeral.initializeSeparator(response.user.account.creditList[i], true));
                        $scope.account.thirdParty.account.idList.value.push(response.user.account.thirdParty.account.id);
                        $scope.account.thirdParty.account.usernameList.value.push(response.user.account.thirdParty.account.username);
                        $scope.account.thirdParty.provider.idList.value.push(response.user.account.thirdParty.provider.id);
                        $scope.account.thirdParty.provider.nameList.value.push(response.user.account.thirdParty.provider.name);
                        $scope.account.usernameList.value.push(response.user.account.usernameList[i]);

                    }

                    if(response.user.avatar != "") {

                        $scope.avatar.upload.file = document.getElementById("global").getAttribute("data-image-url") + "/user/" + response.user.avatar;
                        $scope.avatar.upload.view = true;

                    }

                    $scope.city.value = response.user.city;

                    for(var i = 0; i < response.user.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.user.company.idList[i]);
                        $scope.company.nameList.value.push(response.user.company.nameList[i]);

                    }

                    for(var i = 0; i < response.user.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.user.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.user.company.branch.nameList[i]);

                    }

                    var createdTimestamp = new Date(response.user.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);

                    $scope.created.user.id.value = response.user.created.user.id;
                    $scope.created.user.username.value = response.user.created.user.username;
                    $scope.credit.main.value = library.numeral.initializeSeparator(response.user.credit.main, true);
                    $scope.credit.promotion.value = library.numeral.initializeSeparator(response.user.credit.promotion, true);
                    $scope.contact.emailAddress.value = response.user.contact.emailAddress;
                    $scope.contact.faxNumber.value = response.user.contact.faxNumber;
                    $scope.contact.lineId.value = response.user.contact.lineId;
                    $scope.contact.phoneNumber.value = response.user.contact.phoneNumber;
                    $scope.contact.whatsappNumber.value = response.user.contact.whatsappNumber;
                    $scope.contact.wechatId.value = response.user.contact.wechatId;
                    $scope.country.value = $scope.camelCaseToStandardCase(response.user.country);
                    $scope.gender.value = $scope.camelCaseToStandardCase(response.user.gender);
                    $scope.group.value = response.user.group.name;
                    $scope.id.value = response.user.id;
                    $scope.language.value = $scope.camelCaseToStandardCase(response.user.language);
                    $scope.name.first.value = response.user.name.first;
                    $scope.name.last.value = response.user.name.last;
                    $scope.name.middle.value = response.user.name.middle;

                    var modifiedTimestamp = new Date(response.user.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);

                    $scope.password.main.value = library.encryption.rsaDecrypt(response.user.password.main);

                    for(var i = 0; i < response.user.payment.nameList.length; i++) {

                        $scope.payment.index.push(i);
                        $scope.payment.branchList.value.push(response.user.payment.branchList[i]);

                        $scope.payment.method.push({
                            "value": response.user.payment.method.nameList[i]
                        });

                        $scope.payment.nameList.value.push(response.user.payment.nameList[i]);
                        $scope.payment.numberList.value.push(response.user.payment.numberList[i]);

                    }

                    $scope.reference.value = response.user.reference;
                    $scope.role.value = response.user.role.name;
                    $scope.state.value = response.user.state;
                    $scope.status.value = $scope.camelCaseToStandardCase(response.user.status);
                    $scope.streetAddress.value = response.user.streetAddress;
                    $scope.type.value = $scope.camelCaseToStandardCase(response.user.type);
                    $scope.username.value = response.user.username;
                    $scope.zipCode.value = response.user.zipCode;

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

                    $scope.initializePrivilege(response.user.privilege);

                }

                $scope.popup.view = true;
                $scope.popup.user = true;

                $scope.rebuild();

            } else {

                $scope.response.class = "error";
                $scope.response.message = response.response;
                $scope.response.view = true;

            }

            $scope.loading.view = false;

            $scope.hideResponse();

        });

    }


    $scope.removeFilterPagination = function(event) {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/remove-filter-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.reload();

            } else {

                $scope.response.class = "error";
                $scope.response.message = response.response;
                $scope.response.view = true;

                $scope.loading.view = true;

            }

            $scope.hideResponse();

        });

        event.preventDefault();

    }


    $scope.setPagination = function(event) {

        $scope.loading.view = true;

        var rest = {
            "data": $scope.site.page.size,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/set-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/user/";

            } else {

                $scope.response.class = "error";
                $scope.response.message = response.response;
                $scope.response.view = true;

            }

            $scope.hideResponse();

        });

        event.preventDefault();

    }


    $scope.uploadAvatar = function() {

        $scope.loading.view = true;

        var formData = new FormData();

        angular.forEach($scope.files, function(value) {

            formData.append("file", value);

        });

        var rest = {
            "data": formData,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/user/upload-avatar"
        };
        global.restMultipart(rest, function(response) {

            if(response.result) {

                $scope.response.class = "success";

                $scope.avatar.value = response.imageList[0];
                $scope.avatar.upload.file = response.imageList[0];
                $scope.avatar.upload.view = true;

            } else {

                $scope.response.class = "error";

            }

            $scope.loading.view = false;

            $scope.response.message = response.response;
            $scope.response.view = true;

            $scope.hideResponse();

        });

    }


}]);


app.directive("userDetail", function() {


    var user = {};


    user.templateUrl = document.getElementById("global").getAttribute("data-base-url") + "/html/user-detail-popup.html";


    return user;


});


app.directive("userAvatarPreview", function() {


    var user = {};


    user.template = "<span ng-if=\"avatar.upload.file != ''\">" +
        "<img class=\"responsive\" ng-src=\"" + document.getElementById("global").getAttribute("data-image-url") + "/user/{{avatar.upload.file}}\" />" +
        "</span>";


    return user;


});
