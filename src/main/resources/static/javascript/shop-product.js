document.addEventListener("DOMContentLoaded", function(event) {


    tinymce.init({
        "branding": false,
        "convert_urls": true,
        "height": 300,
        "images_upload_url": document.getElementById("global").getAttribute("data-base-url") + "/shop/product/upload-tinymce",
        "image_advtab": true,
        "plugins": "preview searchreplace autolink directionality visualblocks visualchars image link media template codesample table charmap hr pagebreak nonbreaking anchor toc insertdatetime advlist lists textcolor wordcount imagetools contextmenu colorpicker textpattern",
        "relative_urls": false,
        "remove_script_host": false,
        "selector": "#tinymce-1",
        "toolbar1": "formatselect | bold italic strikethrough forecolor backcolor | link | alignleft aligncenter alignright alignjustify  | numlist bullist outdent indent  | removeformat"
    });


    tinymce.init({
        "branding": false,
        "convert_urls": true,
        "height": 300,
        "images_upload_url": document.getElementById("global").getAttribute("data-base-url") + "/shop/product/upload-tinymce",
        "image_advtab": true,
        "plugins": "preview searchreplace autolink directionality visualblocks visualchars image link media template codesample table charmap hr pagebreak nonbreaking anchor toc insertdatetime advlist lists textcolor wordcount imagetools contextmenu colorpicker textpattern",
        "relative_urls": false,
        "remove_script_host": false,
        "selector": "#tinymce-2",
        "toolbar1": "formatselect | bold italic strikethrough forecolor backcolor | link | alignleft aligncenter alignright alignjustify  | numlist bullist outdent indent  | removeformat"
    });


    tinymce.init({
        "branding": false,
        "convert_urls": true,
        "height": 300,
        "images_upload_url": document.getElementById("global").getAttribute("data-base-url") + "/shop/product/upload-tinymce",
        "image_advtab": true,
        "plugins": "preview searchreplace autolink directionality visualblocks visualchars image link media template codesample table charmap hr pagebreak nonbreaking anchor toc insertdatetime advlist lists textcolor wordcount imagetools contextmenu colorpicker textpattern",
        "relative_urls": false,
        "remove_script_host": false,
        "selector": "#tinymce-3",
        "toolbar1": "formatselect | bold italic strikethrough forecolor backcolor | link | alignleft aligncenter alignright alignjustify  | numlist bullist outdent indent  | removeformat"
    });


});


app.controller("shopProduct", ["$scope", "$window", "global", function($scope, $window, global) {


    $scope.brand = {
        "option": [
            {"id": "", "name": "Brand", "path": "", "url": ""}
        ],
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.brand.selected = $scope.brand.option[0];

    $scope.category = {
        "idList": {
            "value": []
        },
        "nameList": {
            "value": []
        },
        "option": [],
        "pathList": {
            "value": []
        },
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "urlList": {
            "value": []
        }
    };

    $scope.dislike = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.featured = {
        "name": "",
        "value": false
    };

    $scope.filter = {
        "brand": {
            "option": [
                {"id": "", "name": "Brand"}
            ]
        },
        "category": {
            "option": [
                {"icon": "", "id": "", "name": "Category"}
            ]
        },
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

    $scope.filter.brand.selected = $scope.filter.brand.option[0];

    $scope.filter.category.selected = $scope.filter.category.option[0];

    $scope.filter.status.selected = $scope.filter.status.option[0];

    $scope.guide = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.information = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.like = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.name = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.pathList = {
        "value": []
    };

    $scope.price = {
        "exchange": {
            "buy": {
                "response": {
                    "class": "",
                    "value": "",
                    "view": false
                },
                "value": ""
            },
            "sell": {
                "response": {
                    "class": "",
                    "value": "",
                    "view": false
                },
                "value": ""
            }
        },
        "oneTime": {
            "discount": {
                "response": {
                    "class": "",
                    "value": "",
                    "view": false
                },
                "value": ""
            },
            "normal": {
                "response": {
                    "class": "",
                    "value": "",
                    "view": false
                },
                "value": ""
            }
        },
        "recurring": {
            "discountList": {
                "value": []
            },
            "normalList": {
                "value": []
            },
            "response": {
                "class": "",
                "value": "",
                "view": false
            }
        }
    };

    $scope.rate = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.recurring = {
        "name": "",
        "timeFrame": [],
        "value": false
    };

    $scope.sequence = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.sku = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.stock = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.storage = {
        "name": "",
        "value": false
    };

    $scope.tagList = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": []
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

    $scope.url = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.variant = {
        "index": [],
        "nameList": {
            "value": []
        },
        "price": {
            "oneTime": {
                "discountList": {
                    "value": []
                },
                "normalList": {
                    "value": []
                }
            },
            "recurring": {
                "discountList": {
                    "value": []
                },
                "normalList": {
                    "value": []
                }
            }
        },
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "skuList": {
            "value": []
        },
        "stockList": {
            "value": []
        }
    };

    $scope.valid = Object.assign($scope.valid, {
        "brand": false,
        "category": false,
        "dislike": false,
        "like": false,
        "name": false,
        "priceExchangeBuy": false,
        "priceExchangeSell": false,
        "priceOneTimeDiscount": false,
        "priceOneTimeNormal": false,
        "priceRecurringDiscountList": false,
        "priceRecurringNormalList": false,
        "rate": false,
        "sequence": false,
        "stock": false,
        "sku": false,
        "type": false,
        "url": false,
        "variant": false,
        "view": false
    });

    $scope.view = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };


    $scope.categoryFilterOptionHierarchy = function(list, parentId, level, selectedId) {

        angular.forEach(list, function(value, key) {

            if(value.parent.id == parentId) {

                var icon = "";

                for(var i = 0; i <= level; i++) {

                    icon += "<i class=\"double-arrow-right-white square-10 margin-right-5\"></i>";

                }

                $scope.filter.category.option.push({
                    "icon": icon,
                    "id": value.id,
                    "name": value.name
                });

                if(value.id == selectedId) {

                    $scope.filter.category.selected = $scope.filter.category.option[$scope.filter.category.option.length - 1];

                }

                level++;

                $scope.categoryFilterOptionHierarchy(list, value.id, level, selectedId);

                level--;

            }

        });

    }


    $scope.categoryOptionHierarchy = function(list, parentId, level) {

        angular.forEach(list, function(value, key) {

            if(value.parent.id == parentId) {

                var icon = "";

                for(var i = 0; i <= level; i++) {

                    icon += "<i class=\"double-arrow-right-white square-10 margin-right-5\"></i>";

                }

                $scope.category.option.push({
                    "icon": icon,
                    "id": value.id,
                    "name": value.name,
                    "path": value.path,
                    "url": value.url
                });

                level++;

                $scope.categoryOptionHierarchy(list, value.id, level);

                level--;

            }

        });

    }


    $scope.categoryToggleCheckbox = function(category) {

        var index = $scope.category.idList.value.indexOf(category.id);

        if(index > -1) {

            $scope.category.idList.value.splice(index, 1);
            $scope.category.nameList.value.splice(index, 1);
            $scope.category.pathList.value.splice(index, 1);
            $scope.category.urlList.value.splice(index, 1);

        } else {

            $scope.category.idList.value.push(category.id);
            $scope.category.nameList.value.push(category.name);
            $scope.category.pathList.value.push(category.path);
            $scope.category.urlList.value.push(category.url);

        }

    }


    $scope.checkBrand = function() {

        if($scope.brand.selected.id == "") {

            $scope.brand.response.value = "Please select brand";
            $scope.brand.response.class = "error";
            $scope.brand.response.view = true;
            $scope.valid.brand = false;

        } else {

            $scope.brand.response.view = false;
            $scope.valid.brand = true;

        }

    }


    $scope.checkCategory = function() {

        if($scope.category.idList.value.length == 0) {

            $scope.category.response.value = "Please select at least 1 category";
            $scope.category.response.class = "error";
            $scope.category.response.view = true;
            $scope.valid.category = false;

        } else {

            $scope.category.response.view = false;
            $scope.valid.category = true;

        }

    }


    $scope.checkData = function() {

        $scope.checkBrand();

        $scope.checkCategory();

        $scope.checkCompany();

        $scope.checkCompanyBranch();

        $scope.checkDislike();

        $scope.checkLike();

        $scope.checkName();

        if($scope.type.selected.value == 'Currency') {

            $scope.checkPriceExchangeBuy();

            $scope.checkPriceExchangeSell();

            $scope.valid.brand = true;
            $scope.valid.priceOneTimeDiscount = true;
            $scope.valid.priceOneTimeNormal = true;
            $scope.valid.priceRecurringDiscountList = true;
            $scope.valid.priceRecurringNormalList = true;

        } else if(($scope.type.selected.value == 'Goods' || $scope.type.selected.value == 'Service') && !$scope.recurring.value) {

            $scope.checkPriceOneTimeDiscount();

            $scope.checkPriceOneTimeNormal();

            $scope.checkVariantPriceOneTimeDiscountList();

            $scope.checkVariantPriceOneTimeNormalList();

            $scope.valid.priceExchangeBuy = true;
            $scope.valid.priceExchangeSell = true;
            $scope.valid.priceOneTimeDiscount = true;
            $scope.valid.priceOneTimeNormal = true;

        } else if(($scope.type.selected.value == 'Goods' || $scope.type.selected.value == 'Service') && $scope.recurring.value) {

            $scope.checkPriceRecurringDiscountList();

            $scope.checkPriceRecurringNormalList();

            $scope.checkVariantPriceRecurringDiscountList();

            $scope.checkVariantPriceRecurringNormalList();

            $scope.valid.priceExchangeBuy = true;
            $scope.valid.priceExchangeSell = true;
            $scope.valid.priceRecurringDiscountList = true;
            $scope.valid.priceRecurringNormalList = true;

        }

        $scope.checkRate();

        $scope.checkSequence();

        $scope.checkSku();

        $scope.checkStatus();

        if($scope.storage.value) {

            $scope.checkStock();

            $scope.checkVariantStockList();

        } else {

            $scope.valid.stock = true;

        }

        $scope.checkType();

        $scope.checkUrl();

        $scope.checkVariantNameList();

        $scope.checkVariantSkuList();

        $scope.checkView();

    }


    $scope.checkDislike = function() {

        if($scope.dislike.value != "") {

            if(!$scope.dislike.value.match(/^[0-9,]+$/)) {

                $scope.dislike.response.value = "Please enter only number";
                $scope.dislike.response.class = "error";
                $scope.dislike.response.view = true;
                $scope.valid.dislike = false;

            } else {

                $scope.dislike.response.view = false;
                $scope.valid.dislike = true;

            }

        } else {

            $scope.dislike.response.view = false;
            $scope.valid.dislike = true;

        }

        $scope.dislike.value = library.numeral.initializeSeparator($scope.dislike.value, false);

    }


    $scope.checkLike = function() {

        if($scope.like.value != "") {

            if(!$scope.like.value.match(/^[0-9,]+$/)) {

                $scope.like.response.value = "Please enter only number";
                $scope.like.response.class = "error";
                $scope.like.response.view = true;
                $scope.valid.like = false;

            } else {

                $scope.like.response.view = false;
                $scope.valid.like = true;

            }

        } else {

            $scope.like.response.view = false;
            $scope.valid.like = true;

        }

        $scope.like.value = library.numeral.initializeSeparator($scope.like.value, false);

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


    $scope.checkPriceExchangeBuy = function() {

        if(!$scope.price.exchange.buy.value.match(/^[0-9,.]+$/)) {

            $scope.price.exchange.buy.response.value = "Please enter decimal amount";
            $scope.price.exchange.buy.response.class = "error";
            $scope.price.exchange.buy.response.view = true;
            $scope.valid.priceExchangeBuy = false;

        } else {

            $scope.price.exchange.buy.response.view = false;
            $scope.valid.priceExchangeBuy = true;

        }

        $scope.price.exchange.buy.value = library.numeral.initializeSeparator($scope.price.exchange.buy.value, false);

    }


    $scope.checkPriceExchangeSell = function() {

        if(!$scope.price.exchange.sell.value.match(/^[0-9,.]+$/)) {

            $scope.price.exchange.sell.response.value = "Please enter decimal amount";
            $scope.price.exchange.sell.response.class = "error";
            $scope.price.exchange.sell.response.view = true;
            $scope.valid.priceExchangeSell = false;

        } else {

            $scope.price.exchange.sell.response.view = false;
            $scope.valid.priceExchangeSell = true;

        }

        $scope.price.exchange.sell.value = library.numeral.initializeSeparator($scope.price.exchange.sell.value, false);

    }


    $scope.checkPriceOneTimeDiscount = function() {

        if($scope.price.oneTime.discount.value != "") {

            if(!$scope.price.oneTime.discount.value.match(/^[0-9,.]+$/)) {

                $scope.price.oneTime.discount.response.value = "Please enter decimal amount";
                $scope.price.oneTime.discount.response.class = "error";
                $scope.price.oneTime.discount.response.view = true;
                $scope.valid.priceOneTimeDiscount = false;

            } else {

                $scope.price.oneTime.discount.response.view = false;
                $scope.valid.priceOneTimeDiscount = true;

            }

        } else {

            $scope.price.oneTime.discount.response.view = false;
            $scope.valid.priceOneTimeDiscount = true;

        }

        $scope.price.oneTime.discount.value = library.numeral.initializeSeparator($scope.price.oneTime.discount.value, false);

    }


    $scope.checkPriceOneTimeNormal = function() {

        if(!$scope.price.oneTime.normal.value.match(/^[0-9,.]+$/)) {

            $scope.price.oneTime.normal.response.value = "Please enter decimal amount";
            $scope.price.oneTime.normal.response.class = "error";
            $scope.price.oneTime.normal.response.view = true;
            $scope.valid.priceOneTimeNormal = false;

        } else {

            $scope.price.oneTime.normal.response.view = false;
            $scope.valid.priceOneTimeNormal = true;

        }

        $scope.price.oneTime.normal.value = library.numeral.initializeSeparator($scope.price.oneTime.normal.value, false);

    }


    $scope.checkPriceRecurringDiscountList = function() {

        for(var i = 0; i < $scope.recurring.timeFrame.length; i++) {

            if($scope.price.recurring.discountList.value[i] != "") {

                if(!$scope.price.recurring.discountList.value[i].match(/^[0-9,.]+$/)) {

                    $scope.price.recurring.response.value = "Please enter decimal amount";
                    $scope.price.recurring.response.class = "error";
                    $scope.price.recurring.response.view = true;
                    $scope.valid.priceRecurringDiscountList = false;

                    break;

                } else {

                    $scope.price.recurring.response.view = false;
                    $scope.valid.priceRecurringDiscountList = true;

                }

            } else {

                $scope.price.recurring.response.view = false;
                $scope.valid.priceRecurringDiscountList = true;

            }

            $scope.price.recurring.discountList.value[i] = library.numeral.initializeSeparator($scope.price.recurring.discountList.value[i], false);

        }

    }


    $scope.checkPriceRecurringNormalList = function() {

        for(var i = 0; i < $scope.recurring.timeFrame.length; i++) {

            if(!$scope.price.recurring.normalList.value[i].match(/^[0-9,.]+$/)) {

                $scope.price.recurring.response.value = "Please enter decimal amount";
                $scope.price.recurring.response.class = "error";
                $scope.price.recurring.response.view = true;
                $scope.valid.priceRecurringNormalList = false;

                break;

            } else {

                $scope.price.recurring.response.view = false;
                $scope.valid.priceRecurringNormalList = true;

            }

            $scope.price.recurring.normalList.value[i] = library.numeral.initializeSeparator($scope.price.recurring.normalList.value[i], false);

        }

    }


    $scope.checkRate = function() {

        if($scope.rate.value != "") {

            if(!$scope.rate.value.match(/^[0-9,]+$/)) {

                $scope.rate.response.value = "Please enter only number";
                $scope.rate.response.class = "error";
                $scope.rate.response.view = true;
                $scope.valid.rate = false;

            } else {

                $scope.rate.response.view = false;
                $scope.valid.rate = true;

            }

        } else {

            $scope.rate.response.view = false;
            $scope.valid.rate = true;

        }

        $scope.rate.value = library.numeral.initializeSeparator($scope.rate.value, false);

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


    $scope.checkSku = function() {

        if($scope.sku.value != "") {

            if($scope.sku.value.length < 3 || $scope.sku.value.length > 20) {

                $scope.sku.response.value = "Please enter between 3 - 20 characters";
                $scope.sku.response.class = "error";
                $scope.sku.response.view = true;
                $scope.valid.sku = false;

            } else {

                $scope.sku.response.view = false;
                $scope.valid.sku = true;

            }

        } else {

            $scope.sku.response.view = false;
            $scope.valid.sku = true;

        }

    }


    $scope.checkStock = function() {

        if(!$scope.stock.value.match(/^[0-9,]+$/)) {

            $scope.stock.response.value = "Please enter only number";
            $scope.stock.response.class = "error";
            $scope.stock.response.view = true;
            $scope.valid.stock = false;

        } else {

            $scope.stock.response.view = false;
            $scope.valid.stock = true;

        }

        $scope.stock.value = library.numeral.initializeSeparator($scope.stock.value, false);

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


    $scope.checkUrl = function() {

        if($scope.url.value != "") {

            if($scope.url.value.startsWith("https://")) {

                $scope.url.response.value = "Please enter without https://";
                $scope.url.response.class = "error";
                $scope.url.response.view = true;
                $scope.valid.url = false;

            } else if($scope.url.value.startsWith("http://")) {

                $scope.url.response.value = "Please enter without http://";
                $scope.url.response.class = "error";
                $scope.url.response.view = true;
                $scope.valid.url = false;

            } else if($scope.url.value.startsWith("www.")) {

                $scope.url.response.value = "Please enter without www.";
                $scope.url.response.class = "error";
                $scope.url.response.view = true;
                $scope.valid.url = false;

            } else {

                $scope.url.response.view = false;
                $scope.valid.url = true;

            }

        } else {

            $scope.url.response.view = false;
            $scope.valid.url = true;

        }

    }


    $scope.checkVariantNameList = function() {

        if($scope.variant.index.length > 0) {

            for(var i = 0; i < $scope.variant.index.length; i++) {

                if($scope.variant.nameList.value[i].length < 2) {

                    $scope.variant.response.value = "Please enter variant name more than 2 characters";
                    $scope.variant.response.class = "error";
                    $scope.variant.response.view = true;
                    $scope.valid.variant = false;

                    break;

                } else {

                    $scope.variant.response.view = false;
                    $scope.valid.variant = true;

                }

            }

        } else {

            $scope.variant.response.view = false;
            $scope.valid.variant = true;

        }

    }


    $scope.checkVariantPriceOneTimeDiscountList = function() {

        if($scope.variant.index.length > 0) {

            for(var i = 0; i < $scope.variant.index.length; i++) {

                if(!$scope.variant.price.oneTime.discountList.value[i].match(/^[0-9,.]+$/)) {

                    $scope.variant.response.value = "Please enter decimal amount";
                    $scope.variant.response.class = "error";
                    $scope.variant.response.view = true;
                    $scope.valid.variant = false;

                    break;

                } else {

                    $scope.variant.response.view = false;
                    $scope.valid.variant = true;

                }

                $scope.variant.price.oneTime.discountList.value[i] = library.numeral.initializeSeparator($scope.variant.price.oneTime.discountList.value[i], false);

            }

        } else {

            $scope.variant.response.view = false;
            $scope.valid.variant = true;

        }

    }


    $scope.checkVariantPriceOneTimeNormalList = function() {

        if($scope.variant.index.length > 0) {

            for(var i = 0; i < $scope.variant.index.length; i++) {

                if(!$scope.variant.price.oneTime.normalList.value[i].match(/^[0-9,.]+$/)) {

                    $scope.variant.response.value = "Please enter decimal amount";
                    $scope.variant.response.class = "error";
                    $scope.variant.response.view = true;
                    $scope.valid.variant = false;

                    break;

                } else {

                    $scope.variant.response.view = false;
                    $scope.valid.variant = true;

                }

                $scope.variant.price.oneTime.normalList.value[i] = library.numeral.initializeSeparator($scope.variant.price.oneTime.normalList.value[i], false);

            }

        } else {

            $scope.variant.response.view = false;
            $scope.valid.variant = true;

        }

    }


    $scope.checkVariantPriceRecurringDiscountList = function() {

        if($scope.variant.index.length > 0) {

            for(var i = 0; i < $scope.variant.index.length; i++) {

                for(var j = 0; j < $scope.recurring.timeFrame.length; j++) {

                    if($scope.variant.price.recurring.discountList.value[i][j] != "") {

                        if(!$scope.variant.price.recurring.discountList.value[i][j].match(/^[0-9,.]+$/)) {

                            $scope.variant.response.value = "Please enter decimal amount";
                            $scope.variant.response.class = "error";
                            $scope.variant.response.view = true;
                            $scope.valid.variant = false;

                            break;

                        } else {

                            $scope.variant.response.view = false;
                            $scope.valid.variant = true;

                        }

                    } else {

                        $scope.variant.response.view = false;
                        $scope.valid.variant = true;

                    }

                    $scope.variant.price.recurring.discountList.value[i][j] = library.numeral.initializeSeparator($scope.variant.price.recurring.discountList.value[i][j], false);

                }

            }

        } else {

            $scope.variant.response.view = false;
            $scope.valid.variant = true;

        }

    }


    $scope.checkVariantPriceRecurringNormalList = function() {

        if($scope.variant.index.length > 0) {

            for(var i = 0; i < $scope.variant.index.length; i++) {

                for(var j = 0; j < $scope.recurring.timeFrame.length; j++) {

                    if($scope.variant.price.recurring.normalList.value[i][j] != "") {

                        if(!$scope.variant.price.recurring.normalList.value[i][j].match(/^[0-9,.]+$/)) {

                            $scope.variant.response.value = "Please enter decimal amount";
                            $scope.variant.response.class = "error";
                            $scope.variant.response.view = true;
                            $scope.valid.variant = false;

                            break;

                        } else {

                            $scope.variant.response.view = false;
                            $scope.valid.variant = true;

                        }

                    } else {

                        $scope.variant.response.view = false;
                        $scope.valid.variant = true;

                    }

                    $scope.variant.price.recurring.normalList.value[i][j] = library.numeral.initializeSeparator($scope.variant.price.recurring.normalList.value[i][j], false);

                }

            }

        } else {

            $scope.variant.response.view = false;
            $scope.valid.variant = true;

        }

    }


    $scope.checkVariantSkuList = function() {

        if($scope.variant.index.length > 0) {

            for(var i = 0; i < $scope.variant.index.length; i++) {

                $scope.variant.response.class = "error";
                $scope.variant.response.view = false;
                $scope.valid.variant = true;

            }

        } else {

            $scope.variant.response.view = false;
            $scope.valid.variant = true;

        }

    }


    $scope.checkVariantStockList = function() {

        if($scope.variant.index.length > 0) {

            for(var i = 0; i < $scope.variant.index.length; i++) {

                if(!$scope.variant.stockList.value[i].match(/^[0-9,]+$/)) {

                    $scope.variant.response.value = "Please enter variant stock only number";
                    $scope.variant.response.class = "error";
                    $scope.variant.response.view = true;
                    $scope.valid.variant = false;

                    break;

                } else {

                    $scope.variant.response.view = false;
                    $scope.valid.variant = true;

                }

                $scope.variant.stockList.value[i] = library.numeral.initializeSeparator($scope.variant.stockList.value[i], false);

            }

        } else {

            $scope.variant.response.view = false;
            $scope.valid.variant = true;

        }

    }


    $scope.checkView = function() {

        if($scope.view.value != "") {

            if(!$scope.view.value.match(/^[0-9,]+$/)) {

                $scope.view.response.value = "Please enter only number";
                $scope.view.response.class = "error";
                $scope.view.response.view = true;
                $scope.valid.view = false;

            } else {

                $scope.view.response.view = false;
                $scope.valid.view = true;

            }

        } else {

            $scope.view.response.view = false;
            $scope.valid.view = true;

        }

        $scope.view.value = library.numeral.initializeSeparator($scope.view.value, false);

    }


    $scope.delete = function(id, event) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/product/delete"
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


    $scope.deleteVariant = function(key, event) {

        $scope.variant.index.splice(key, 1);
        $scope.variant.nameList.value.splice(key, 1);
        $scope.variant.price.oneTime.discountList.value.splice(key, 1);
        $scope.variant.price.oneTime.normalList.value.splice(key, 1);
        $scope.variant.price.recurring.discountList.value.splice(key, 1);
        $scope.variant.price.recurring.normalList.value.splice(key, 1);
        $scope.variant.skuList.value.splice(key, 1);
        $scope.variant.stockList.value.splice(key, 1);

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
                    "brand": {
                        "id": $scope.brand.selected.id,
                        "name": $scope.brand.selected.name,
                        "path": $scope.brand.selected.path,
                        "url": $scope.brand.selected.url
                    },
                    "category": {
                        "idList": $scope.category.idList.value,
                        "nameList": $scope.category.nameList.value,
                        "pathList": $scope.category.pathList.value,
                        "urlList": $scope.category.urlList.value
                    },
                    "company": {
                        "branch": {
                            "idList": $scope.company.branch.idList.value,
                            "nameList": $scope.company.branch.nameList.value
                        },
                        "idList": $scope.company.idList.value,
                        "nameList": $scope.company.nameList.value
                    },
                    "description": tinyMCE.get("tinymce-1").getContent(),
                    "dislike": input.dislike,
                    "featured": $scope.featured.value,
                    "guide": tinyMCE.get("tinymce-3").getContent(),
                    "imageList": $scope.imageList.value,
                    "information": tinyMCE.get("tinymce-2").getContent(),
                    "like": input.like,
                    "meta": {
                        "description": $scope.meta.description.value,
                        "keyword": $scope.meta.keyword.value,
                        "title": $scope.meta.title.value
                    },
                    "name": $scope.name.value,
                    "og": {
                        "description": $scope.og.description.value,
                        "title": $scope.og.title.value
                    },
                    "price": {
                        "exchange": {
                            "buy": input.price.exchange.buy,
                            "sell": input.price.exchange.sell
                        },
                        "oneTime": {
                            "discount": input.price.oneTime.discount,
                            "normal": input.price.oneTime.normal
                        },
                        "recurring": {
                            "discountList": input.price.recurring.discountList,
                            "normalList": input.price.recurring.normalList
                        }
                    },
                    "rate": input.rate,
                    "recurring": $scope.recurring.value,
                    "sequence": input.sequence,
                    "sku": $scope.sku.value,
                    "status": $scope.status.selected.value,
                    "stock": input.stock,
                    "storage": $scope.storage.value,
                    "tagList": $scope.tagList.value,
                    "thumbnailList": $scope.thumbnailList.value,
                    "type": $scope.type.selected.value,
                    "url": $scope.url.value,
                    "variant": {
                        "nameList": $scope.variant.nameList.value,
                        "price": {
                            "oneTime": {
                                "discountList": input.variant.price.oneTime.discountList,
                                "normalList": input.variant.price.oneTime.normalList
                            },
                            "recurring": {
                                "discountList": input.variant.price.recurring.discountList,
                                "normalList": input.variant.price.recurring.normalList
                            }
                        },
                        "skuList": $scope.variant.skuList.value,
                        "stockList": input.variant.stockList
                    },
                    "view": input.view
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/product/update"
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
            "brand.id": ["equal", $scope.filter.brand.selected.id],
            "category.idList": ["equal", $scope.filter.category.selected.id],
            "created.timestamp": ["between", "date", filterCreatedDate[0], filterCreatedDate[1]],
            "name": ["like", $scope.filter.name.value],
            "status": ["equal", ""]
        };

        if($scope.filter.status.selected.value != "Unknown") {

            data["status"][1] = $scope.filter.status.selected.value;

        }

        var rest = {
            "data": data,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/product/filter-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/shop/product/";

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


    $scope.forceUploadImageList = function() {

        document.getElementsByClassName("shop-product-image-list")[0].click();

    }


    $scope.forceUploadThumbnailList = function() {

        document.getElementsByClassName("shop-product-thumbnail-list")[0].click();

    }


    $scope.goToPage = function(event) {

        if(event.which == 13) {

            $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/shop/product/page-" + $scope.site.page.number + "/";

        }

        event.preventDefault();

    }


    $scope.initializeData = function(id) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/product/initialize-data"
        }
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.shopProduct.id != null) {

                    for(var i = 0; i < response.shopProduct.category.idList.length; i++) {

                        $scope.category.idList.value.push(response.shopProduct.category.idList[i]);
                        $scope.category.nameList.value.push(response.shopProduct.category.nameList[i]);
                        $scope.category.pathList.value.push(response.shopProduct.category.pathList[i]);
                        $scope.category.urlList.value.push(response.shopProduct.category.urlList[i]);

                    }

                    for(var i = 0; i < response.shopProduct.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.shopProduct.company.idList[i]);
                        $scope.company.nameList.value.push(response.shopProduct.company.nameList[i]);

                    }

                    for(var i = 0; i < response.shopProduct.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.shopProduct.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.shopProduct.company.branch.nameList[i]);

                    }

                    var createdTimestamp = new Date(response.shopProduct.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);
                    $scope.created.timestamp.view = true;

                    $scope.created.user.id.value = response.shopProduct.created.user.id;
                    $scope.created.user.username.value = response.shopProduct.created.user.username;
                    $scope.dislike.value = library.numeral.initializeSeparator(response.shopProduct.dislike, true);
                    $scope.featured.value = response.shopProduct.featured;
                    $scope.id.value = response.shopProduct.id;

                    if(response.shopProduct.imageList.length > 0) {

                        $scope.imageList.value = response.shopProduct.imageList;
                        $scope.imageList.upload.file = response.shopProduct.imageList;
                        $scope.imageList.upload.view = true;

                    }

                    $scope.like.value = library.numeral.initializeSeparator(response.shopProduct.like, true);
                    $scope.meta.description.value = response.shopProduct.meta.description;
                    $scope.meta.keyword.value = response.shopProduct.meta.keyword;
                    $scope.meta.title.value = response.shopProduct.meta.title;

                    var modifiedTimestamp = new Date(response.shopProduct.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);
                    $scope.modified.timestamp.view = true;

                    $scope.modified.user.id.value = response.shopProduct.modified.user.id;
                    $scope.modified.user.username.value = response.shopProduct.modified.user.username;
                    $scope.name.value = response.shopProduct.name;
                    $scope.og.description.value = response.shopProduct.og.description;
                    $scope.og.title.value = response.shopProduct.og.title;
                    $scope.pathList.value = response.shopProduct.pathList;
                    $scope.price.exchange.buy.value = library.numeral.initializeSeparator(response.shopProduct.price.exchange.buy, true);
                    $scope.price.exchange.sell.value = library.numeral.initializeSeparator(response.shopProduct.price.exchange.sell, true);
                    $scope.price.oneTime.discount.value = library.numeral.initializeSeparator(response.shopProduct.price.oneTime.discount, true);
                    $scope.price.oneTime.normal.value = library.numeral.initializeSeparator(response.shopProduct.price.oneTime.normal, true);
                    $scope.rate.value = library.numeral.initializeSeparator(response.shopProduct.rate, true);
                    $scope.recurring.value = response.shopProduct.recurring;
                    $scope.sequence.value = library.numeral.initializeSeparator(response.shopProduct.sequence, true);
                    $scope.sku.value = response.shopProduct.sku;
                    $scope.stock.value = library.numeral.initializeSeparator(response.shopProduct.stock, true);
                    $scope.storage.value = response.shopProduct.storage;
                    $scope.tagList.value = response.shopProduct.tagList;

                    if(response.shopProduct.thumbnailList.length > 0) {

                        $scope.thumbnailList.value = response.shopProduct.thumbnailList;
                        $scope.thumbnailList.upload.file = response.shopProduct.thumbnailList;
                        $scope.thumbnailList.upload.view = true;

                    }

                    angular.forEach($scope.type.option, function(value, key) {

                        if(value.value == response.shopProduct.type) {

                            $scope.type.selected = $scope.type.option[key];

                            return false;

                        }

                    });

                    $scope.url.value = response.shopProduct.url;

                    for(var i = 0; i < response.shopProduct.variant.nameList.length; i++) {

                        $scope.variant.index.push(i);
                        $scope.variant.nameList.value.push(response.shopProduct.variant.nameList[i]);
                        $scope.variant.price.oneTime.discountList.value.push(library.numeral.initializeSeparator(response.shopProduct.variant.price.oneTime.discountList[i], true));
                        $scope.variant.price.oneTime.normalList.value.push(library.numeral.initializeSeparator(response.shopProduct.variant.price.oneTime.normalList[i], true));
                        $scope.variant.price.recurring.discountList.value[i] = [];
                        $scope.variant.price.recurring.normalList.value[i] = [];

                        angular.forEach(response.priceRecurringList, function(value, key) {

                            $scope.variant.price.recurring.discountList.value[i].push(library.numeral.initializeSeparator(response.shopProduct.variant.price.recurring.discountList[i][key], true));
                            $scope.variant.price.recurring.normalList.value[i].push(library.numeral.initializeSeparator(response.shopProduct.variant.price.recurring.normalList[i][key], true));

                        });

                        $scope.variant.skuList.value.push(response.shopProduct.variant.skuList[i]);
                        $scope.variant.stockList.value.push(library.numeral.initializeSeparator(response.shopProduct.variant.stockList[i], true));

                    }

                    $scope.view.value = library.numeral.initializeSeparator(response.shopProduct.view, true);

                    $scope.initializeSwitch();

                }

                angular.forEach(response.shopBrandList, function(value, key) {

                    $scope.brand.option.push({
                        "id": value.id,
                        "name": value.name,
                        "path": value.path,
                        "url": value.url
                    });

                    if(response.shopProduct.id != null) {

                        if(value.id == response.shopProduct.brand.id) {

                            $scope.brand.selected = $scope.brand.option[key + 1];

                        }

                    }

                });

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

                angular.forEach(response.priceRecurringList, function(value, key) {

                    $scope.recurring.timeFrame.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.shopProduct.id != null) {

                        $scope.price.recurring.discountList.value.push(library.numeral.initializeSeparator(response.shopProduct.price.recurring.discountList[key]));
                        $scope.price.recurring.normalList.value.push(library.numeral.initializeSeparator(response.shopProduct.price.recurring.normalList[key]));

                    } else {

                        $scope.price.recurring.discountList.value.push("");
                        $scope.price.recurring.normalList.value.push("");

                    }

                });

                angular.forEach(response.productTypeList, function(value, key) {

                    $scope.type.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.shopProduct.id != null) {

                        if(value == response.shopProduct.type) {

                            $scope.type.selected = $scope.type.option[(key + 1)];

                        }

                    }

                });

                angular.forEach(response.statusList, function(value, key) {

                    $scope.status.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.shopProduct.id != null) {

                        if(value == response.shopProduct.status) {

                            $scope.status.selected = $scope.status.option[(key + 1)];

                        }

                    }

                });

                $scope.categoryOptionHierarchy(response.shopCategoryList, 0, 0);

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
            "dislike": 0,
            "like": 0,
            "price": {
                "exchange": {
                    "buy": 0,
                    "sell": 0
                },
                "oneTime": {
                    "discount": 0,
                    "normal": 0
                },
                "recurring": {
                    "discountList": [],
                    "normalList": []
                }
            },
            "rate": 0,
            "sequence": 0,
            "stock": 0,
            "variant": {
                "price": {
                    "oneTime": {
                        "discountList": [],
                        "normalList": []
                    },
                    "recurring": {
                        "discountList": [],
                        "normalList": []
                    }
                },
                "stockList": []
            },
            "view": 0
        };

        $scope.dislike.value = library.numeral.initializeSeparator($scope.dislike.value, true);
        $scope.like.value = library.numeral.initializeSeparator($scope.like.value, true);
        $scope.price.exchange.buy.value = library.numeral.initializeSeparator($scope.price.exchange.buy.value, true);
        $scope.price.exchange.sell.value = library.numeral.initializeSeparator($scope.price.exchange.sell.value, true);
        $scope.price.oneTime.discount.value = library.numeral.initializeSeparator($scope.price.oneTime.discount.value, true);
        $scope.price.oneTime.normal.value = library.numeral.initializeSeparator($scope.price.oneTime.normal.value, true);
        $scope.rate.value = library.numeral.initializeSeparator($scope.rate.value, true);
        $scope.sequence.value = library.numeral.initializeSeparator($scope.sequence.value, true);
        $scope.stock.value = library.numeral.initializeSeparator($scope.stock.value, true);
        $scope.view.value = library.numeral.initializeSeparator($scope.view.value, true);

        result.dislike = $scope.dislike.value.replace(/[^0-9]/g, "");
        result.like = $scope.like.value.replace(/[^0-9]/g, "");
        result.price.exchange.buy = $scope.price.exchange.buy.value.replace(/[^0-9.]/g, "");
        result.price.exchange.sell = $scope.price.exchange.sell.value.replace(/[^0-9.]/g, "");
        result.price.oneTime.discount = $scope.price.oneTime.discount.value.replace(/[^0-9.]/g, "");
        result.price.oneTime.normal = $scope.price.oneTime.normal.value.replace(/[^0-9.]/g, "");
        result.rate = $scope.rate.value.replace(/[^0-9.]/g, "");
        result.sequence = $scope.sequence.value.replace(/[^0-9]/g, "");
        result.stock = $scope.stock.value.replace(/[^0-9]/g, "");
        result.view = $scope.view.value.replace(/[^0-9]/g, "");

        for(var i = 0; i < $scope.recurring.timeFrame.length; i++) {

            $scope.price.recurring.discountList.value[i] = library.numeral.initializeSeparator($scope.price.recurring.discountList.value[i], true);
            $scope.price.recurring.normalList.value[i] = library.numeral.initializeSeparator($scope.price.recurring.normalList.value[i], true);

            result.price.recurring.discountList[i] = $scope.price.recurring.discountList.value[i].replace(/[^0-9.]/g, "");
            result.price.recurring.normalList[i] = $scope.price.recurring.normalList.value[i].replace(/[^0-9.]/g, "");

        }

        for(var i = 0; i < $scope.variant.index.length; i++) {

            $scope.variant.price.oneTime.discountList.value[i] = library.numeral.initializeSeparator($scope.variant.price.oneTime.discountList.value[i], true);
            $scope.variant.price.oneTime.normalList.value[i] = library.numeral.initializeSeparator($scope.variant.price.oneTime.normalList.value[i], true);

            result.variant.price.oneTime.discountList[i] = $scope.variant.price.oneTime.discountList.value[i].replace(/[^0-9.]/g, "");
            result.variant.price.oneTime.normalList[i] = $scope.variant.price.oneTime.normalList.value[i].replace(/[^0-9.]/g, "");
            result.variant.price.recurring.discountList[i] = [];
            result.variant.price.recurring.normalList[i] = [];

            for(var j = 0; j < $scope.recurring.timeFrame.length; j++) {

                $scope.variant.price.recurring.discountList.value[i][j] = library.numeral.initializeSeparator($scope.variant.price.recurring.discountList.value[i][j], true);
                $scope.variant.price.recurring.normalList.value[i][j] = library.numeral.initializeSeparator($scope.variant.price.recurring.normalList.value[i][j], true);

                result.variant.price.recurring.discountList[i][j] = $scope.variant.price.recurring.discountList.value[i][j].replace(/[^0-9.]/g, "");
                result.variant.price.recurring.normalList[i][j] = $scope.variant.price.recurring.normalList.value[i][j].replace(/[^0-9.]/g, "");

            }

            $scope.variant.stockList.value[i] = library.numeral.initializeSeparator($scope.variant.stockList.value[i], true);

            result.variant.stockList[i] = $scope.variant.stockList.value[i].replace(/[^0-9]/g, "");

        }

        return result;

    }


    $scope.initializePagination = function() {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/product/initialize-pagination"
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

                angular.forEach(response.shopBrandList, function(value, key) {

                    $scope.filter.brand.option.push({
                        "id": value.id,
                        "name": value.name
                    });

                    if(Object.entries(response.filter).length > 0) {

                        if(value.id == response.filter.brand_id[1]) {

                            $scope.filter.brand.selected = $scope.filter.brand.option[(key + 1)];

                        }

                    }

                });

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

                var selectedId = "";

                if(Object.entries(response.filter).length > 0) {

                    selectedId = response.filter.category_idList[1];

                }

                $scope.categoryFilterOptionHierarchy(response.shopCategoryList, 0, 0, selectedId);

                $scope.site.page.number = response.page;
                $scope.site.page.size = response.size;

            } else {

                $scope.response.class = "error";
                $scope.response.message = response.response;
                $scope.response.view = true;

            }

            $scope.loading.view = false;

            $scope.hideResponse();

        });

    }


    $scope.initializeSwitch = function() {

        if($scope.featured.value) {

            $scope.featured.name = "Enabled";

        } else {

            $scope.featured.name = "Disabled";

        }

        if($scope.recurring.value) {

            $scope.recurring.name = "Enabled";

        } else {

            $scope.recurring.name = "Disabled";

        }

        if($scope.storage.value) {

            $scope.storage.name = "Enabled";

        } else {

            $scope.storage.name = "Disabled";

        }

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
                    "brand": {
                        "id": $scope.brand.selected.id,
                        "name": $scope.brand.selected.name,
                        "path": $scope.brand.selected.path,
                        "url": $scope.brand.selected.url
                    },
                    "category": {
                        "idList": $scope.category.idList.value,
                        "nameList": $scope.category.nameList.value,
                        "pathList": $scope.category.pathList.value,
                        "urlList": $scope.category.urlList.value
                    },
                    "company": {
                        "branch": {
                            "idList": $scope.company.branch.idList.value,
                            "nameList": $scope.company.branch.nameList.value
                        },
                        "idList": $scope.company.idList.value,
                        "nameList": $scope.company.nameList.value
                    },
                    "description": tinyMCE.get("tinymce-1").getContent(),
                    "dislike": input.dislike,
                    "featured": $scope.featured.value,
                    "guide": tinyMCE.get("tinymce-3").getContent(),
                    "imageList": $scope.imageList.value,
                    "information": tinyMCE.get("tinymce-2").getContent(),
                    "like": input.like,
                    "meta": {
                        "description": $scope.meta.description.value,
                        "keyword": $scope.meta.keyword.value,
                        "title": $scope.meta.title.value
                    },
                    "name": $scope.name.value,
                    "og": {
                        "description": $scope.og.description.value,
                        "title": $scope.og.title.value
                    },
                    "price": {
                        "exchange": {
                            "buy": input.price.exchange.buy,
                            "sell": input.price.exchange.sell
                        },
                        "oneTime": {
                            "discount": input.price.oneTime.discount,
                            "normal": input.price.oneTime.normal
                        },
                        "recurring": {
                            "discountList": input.price.recurring.discountList,
                            "normalList": input.price.recurring.normalList
                        }
                    },
                    "rate": input.rate,
                    "recurring": $scope.recurring.value,
                    "sequence": input.sequence,
                    "sku": $scope.sku.value,
                    "status": $scope.status.selected.value,
                    "stock": input.stock,
                    "storage": $scope.storage.value,
                    "tagList": $scope.tagList.value,
                    "thumbnailList": $scope.thumbnailList.value,
                    "type": $scope.type.selected.value,
                    "url": $scope.url.value,
                    "variant": {
                        "nameList": $scope.variant.nameList.value,
                        "price": {
                            "oneTime": {
                                "discountList": input.variant.price.oneTime.discountList,
                                "normalList": input.variant.price.oneTime.normalList
                            },
                            "recurring": {
                                "discountList": input.variant.price.recurring.discountList,
                                "normalList": input.variant.price.recurring.normalList
                            }
                        },
                        "skuList": $scope.variant.skuList.value,
                        "stockList": input.variant.stockList
                    },
                    "view": input.view
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/product/insert"
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


    $scope.insertVariant = function(event) {

        var i = $scope.variant.index.length;
        $scope.variant.index.push(i);
        $scope.variant.nameList.value.push("");
        $scope.variant.price.oneTime.discountList.value.push("");
        $scope.variant.price.oneTime.normalList.value.push("");
        $scope.variant.price.recurring.discountList.value[i] = [];
        $scope.variant.price.recurring.normalList.value[i] = [];

        angular.forEach($scope.recurring.timeFrame, function(value, key) {

            $scope.variant.price.recurring.discountList.value[i].push("");
            $scope.variant.price.recurring.normalList.value[i].push("");

        });

        $scope.variant.skuList.value.push("");
        $scope.variant.stockList.value.push("");

        event.preventDefault();

    }


    $scope.loadDetail = function(id) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/product/initialize-data"
        }
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.shopProduct.id != null) {

                    $scope.brand.value = response.shopProduct.brand.name;

                    for(var i = 0; i < response.shopProduct.category.idList.length; i++) {

                        $scope.category.idList.value.push(response.shopProduct.category.idList[i]);
                        $scope.category.nameList.value.push(response.shopProduct.category.nameList[i]);
                        $scope.category.pathList.value.push(response.shopProduct.category.pathList[i]);
                        $scope.category.urlList.value.push(response.shopProduct.category.urlList[i]);

                    }

                    for(var i = 0; i < response.shopProduct.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.shopProduct.company.idList[i]);
                        $scope.company.nameList.value.push(response.shopProduct.company.nameList[i]);

                    }

                    for(var i = 0; i < response.shopProduct.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.shopProduct.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.shopProduct.company.branch.nameList[i]);

                    }

                    var createdTimestamp = new Date(response.shopProduct.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);

                    $scope.created.user.id.value = response.shopProduct.created.user.id;
                    $scope.created.user.username.value = response.shopProduct.created.user.username;
                    $scope.description.value = response.shopProduct.description;
                    $scope.dislike.value = library.numeral.initializeSeparator(response.shopProduct.dislike, true);
                    $scope.featured.value = response.shopProduct.featured;
                    $scope.guide.value = response.shopProduct.guide;
                    $scope.id.value = response.shopProduct.id;
                    $scope.information.value = response.shopProduct.information;

                    if(response.shopProduct.imageList.length > 0) {

                        angular.forEach(response.shopProduct.imageList, function(value, key) {

                            $scope.imageList.upload.file.push(document.getElementById("global").getAttribute("data-image-url") + "/shop/product/" + value);

                        });

                        $scope.imageList.upload.view = true;

                    }

                    $scope.like.value = library.numeral.initializeSeparator(response.shopProduct.like, true);
                    $scope.meta.description.value = response.shopProduct.meta.description;
                    $scope.meta.keyword.value = response.shopProduct.meta.keyword;
                    $scope.meta.title.value = response.shopProduct.meta.title;

                    var modifiedTimestamp = new Date(response.shopProduct.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);

                    $scope.modified.user.id.value = response.shopProduct.modified.user.id;
                    $scope.modified.user.username.value = response.shopProduct.modified.user.username;
                    $scope.name.value = response.shopProduct.name;
                    $scope.og.description.value = response.shopProduct.og.description;
                    $scope.og.title.value = response.shopProduct.og.title;
                    $scope.pathList.value = response.shopProduct.pathList;
                    $scope.price.exchange.buy.value = library.numeral.initializeSeparator(response.shopProduct.price.exchange.buy, true);
                    $scope.price.exchange.sell.value = library.numeral.initializeSeparator(response.shopProduct.price.exchange.sell, true);
                    $scope.price.oneTime.discount.value = library.numeral.initializeSeparator(response.shopProduct.price.oneTime.discount, true);
                    $scope.price.oneTime.normal.value = library.numeral.initializeSeparator(response.shopProduct.price.oneTime.normal, true);
                    $scope.rate.value = library.numeral.initializeSeparator(response.shopProduct.rate, true);
                    $scope.recurring.value = response.shopProduct.recurring;
                    $scope.sequence.value = library.numeral.initializeSeparator(response.shopProduct.sequence, true);
                    $scope.sku.value = response.shopProduct.sku;
                    $scope.status.value = $scope.camelCaseToStandardCase(response.shopProduct.status);
                    $scope.stock.value = library.numeral.initializeSeparator(response.shopProduct.stock, true);
                    $scope.storage.value = response.shopProduct.storage;
                    $scope.tagList.value = response.shopProduct.tagList;

                    if(response.shopProduct.thumbnailList.length > 0) {

                        angular.forEach(response.shopProduct.thumbnailList, function(value, key) {

                            $scope.thumbnailList.upload.file.push(document.getElementById("global").getAttribute("data-image-url") + "/shop/product/thumbnail/" + value);

                        });

                        $scope.thumbnailList.upload.view = true;

                    }

                    $scope.type.value = $scope.camelCaseToStandardCase(response.shopProduct.type);
                    $scope.url.value = response.shopProduct.url;

                    $scope.variant.index = [];
                    $scope.variant.nameList.value = [];
                    $scope.variant.price.oneTime.discountList.value = [];
                    $scope.variant.price.oneTime.normalList.value = [];
                    $scope.variant.price.recurring.discountList.value = [];
                    $scope.variant.price.recurring.normalList.value = [];
                    $scope.variant.skuList.value = [];
                    $scope.variant.stockList.value = [];

                    for(var i = 0; i < response.shopProduct.variant.nameList.length; i++) {

                        $scope.variant.index.push(i);
                        $scope.variant.nameList.value.push(response.shopProduct.variant.nameList[i]);
                        $scope.variant.price.oneTime.discountList.value.push(library.numeral.initializeSeparator(response.shopProduct.variant.price.oneTime.discountList[i], true));
                        $scope.variant.price.oneTime.normalList.value.push(library.numeral.initializeSeparator(response.shopProduct.variant.price.oneTime.normalList[i], true));
                        $scope.variant.price.recurring.discountList.value[i] = [];
                        $scope.variant.price.recurring.normalList.value[i] = [];

                        angular.forEach(response.priceRecurringList, function(value, key) {

                            $scope.variant.price.recurring.discountList.value[i].push(library.numeral.initializeSeparator(response.shopProduct.variant.price.recurring.discountList[i][key], true));
                            $scope.variant.price.recurring.normalList.value[i].push(library.numeral.initializeSeparator(response.shopProduct.variant.price.recurring.normalList[i][key], true));

                        });

                        $scope.variant.skuList.value.push(response.shopProduct.variant.skuList[i]);
                        $scope.variant.stockList.value.push(library.numeral.initializeSeparator(response.shopProduct.variant.stockList[i], true));

                    }

                    $scope.view.value = library.numeral.initializeSeparator(response.shopProduct.view, true);

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

                    angular.forEach(response.priceRecurringList, function(value, key) {

                        $scope.recurring.timeFrame.push({
                            "name": $scope.camelCaseToStandardCase(value),
                            "value": value
                        });

                        if(response.shopProduct.id != null) {

                            $scope.price.recurring.discountList.value.push(library.numeral.initializeSeparator(response.shopProduct.price.recurring.discountList[key]));
                            $scope.price.recurring.normalList.value.push(library.numeral.initializeSeparator(response.shopProduct.price.recurring.normalList[key]));

                        } else {

                            $scope.price.recurring.discountList.value.push("");
                            $scope.price.recurring.normalList.value.push("");

                        }

                    });

                    $scope.initializeSwitch();

                    $scope.categoryOptionHierarchy(response.shopCategoryList, 0, 0);

                }

                $scope.popup.view = true;
                $scope.popup.shopProduct = true;

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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/product/remove-filter-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.reload();

            } else {

                $scope.response.class = "error";
                $scope.response.message = response.response;
                $scope.response.view = true;

            }

            $scope.hideResponse();

        });

        event.preventDefault();

    }


    $scope.setPagination = function(event) {

        $scope.loading.view = true;

        var rest = {
            "data": $scope.site.page.size,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/product/set-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/shop/product/";

            } else {

                $scope.response.class = "error";
                $scope.response.message = response.response;
                $scope.response.view = true;

            }

            $scope.hideResponse();

        });

        event.preventDefault();

    }


    $scope.uploadImageList = function(index) {

        $scope.loading.view = true;

        var formData = new FormData();

        angular.forEach($scope.files, function(value) {

            formData.append("file", value);

        });

        var rest = {
            "data": formData,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/product/upload-image-list"
        };
        global.restMultipart(rest, function(response) {

            if(response.result) {

                $scope.response.class = "success";

                $scope.imageList.value = response.imageList;
                $scope.imageList.upload.file = response.imageList;
                $scope.imageList.upload.view = true;

            } else {

                $scope.response.class = "error";

            }

            $scope.loading.view = false;

            $scope.response.message = response.response;
            $scope.response.view = true;

            $scope.hideResponse();

        });

    }


    $scope.uploadThumbnailList = function() {

        $scope.loading.view = true;

        var formData = new FormData();

        angular.forEach($scope.files, function(value) {

            formData.append("file", value);

        });

        var rest = {
            "data": formData,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/product/upload-thumbnail-list"
        };
        global.restMultipart(rest, function(response) {

            if(response.result) {

                $scope.response.class = "success";

                $scope.thumbnailList.value = response.imageList;
                $scope.thumbnailList.upload.file = response.imageList;
                $scope.thumbnailList.upload.view = true;

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


app.directive("shopProductDetail", function() {


    var shopProduct = {};


    shopProduct.templateUrl = document.getElementById("global").getAttribute("data-base-url") + "/html/shop-product-detail-popup.html";


    return shopProduct;


});


app.directive("shopProductImageListPreview", function() {


    var shopProduct = {};


    shopProduct.template = "<span ng-repeat=\"imageListValue in imageList.upload.file\">" +
        "<img class=\"responsive\" ng-src=\"" + document.getElementById("global").getAttribute("data-image-url") + "/shop/product/{{imageListValue}}\" />" +
        "</span>";


    return shopProduct;


});


app.directive("shopProductThumbnailListPreview", function() {


    var shopProduct = {};


    shopProduct.template = "<span ng-repeat=\"thumbnailListValue in thumbnailList.upload.file\">" +
        "<img class=\"responsive\" ng-src=\"" + document.getElementById("global").getAttribute("data-image-url") + "/shop/product/thumbnail/{{thumbnailListValue}}\" />" +
        "</span>";


    return shopProduct;


});
