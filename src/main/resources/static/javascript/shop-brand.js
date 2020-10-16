document.addEventListener("DOMContentLoaded", function(event) {


    tinymce.init({
        "branding": false,
        "convert_urls": true,
        "height": 300,
        "images_upload_url": document.getElementById("global").getAttribute("data-base-url") + "/shop/brand/upload-tinymce",
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
        "images_upload_url": document.getElementById("global").getAttribute("data-base-url") + "/shop/brand/upload-tinymce",
        "image_advtab": true,
        "plugins": "preview searchreplace autolink directionality visualblocks visualchars image link media template codesample table charmap hr pagebreak nonbreaking anchor toc insertdatetime advlist lists textcolor wordcount imagetools contextmenu colorpicker textpattern",
        "relative_urls": false,
        "remove_script_host": false,
        "selector": "#tinymce-2",
        "toolbar1": "formatselect | bold italic strikethrough forecolor backcolor | link | alignleft aligncenter alignright alignjustify  | numlist bullist outdent indent  | removeformat"
    });


});


app.controller("shopBrand", ["$scope", "$window", "global", function($scope, $window, global) {


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

    $scope.path = {
        "value": ""
    };

    $scope.rate = {
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

    $scope.url = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.view = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.valid = Object.assign($scope.valid, {
        "category": false,
        "dislike": false,
        "like": false,
        "name": false,
        "rate": false,
        "sequence": false,
        "url": false,
        "view": false
    });


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

        $scope.checkCategory();

        $scope.checkCompany();

        $scope.checkCompanyBranch();

        $scope.checkDislike();

        $scope.checkLike();

        $scope.checkName();

        $scope.checkRate();

        $scope.checkSequence();

        $scope.checkStatus();

        $scope.checkUrl();

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

        $scope.dislike.value = library.numeral.initializeSeparator($scope.dislike.value);

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

        $scope.like.value = library.numeral.initializeSeparator($scope.like.value);

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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/brand/delete"
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
                    "content": tinyMCE.get("tinymce-2").getContent(),
                    "description": tinyMCE.get("tinymce-1").getContent(),
                    "dislike": input.dislike,
                    "imageList": $scope.imageList.value,
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
                    "path": $scope.path.value,
                    "rate": input.rate,
                    "sequence": input.sequence,
                    "status": $scope.status.selected.value,
                    "thumbnailList": $scope.thumbnailList.value,
                    "url": $scope.url.value,
                    "view": input.view
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/brand/update"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/brand/filter-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/shop/brand/";

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

        document.getElementsByClassName("shop-brand-image-list")[0].click();

    }


    $scope.forceUploadThumbnailList = function() {

        document.getElementsByClassName("shop-brand-thumbnail-list")[0].click();

    }


    $scope.goToPage = function(event) {

        if(event.which == 13) {

            $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/shop/brand/page-" + $scope.site.page.number + "/";

        }

        event.preventDefault();

    }


    $scope.initializeData = function(id) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/brand/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.shopBrand.id != null) {

                    for(var i = 0; i < response.shopBrand.category.idList.length; i++) {

                        $scope.category.idList.value.push(response.shopBrand.category.idList[i]);
                        $scope.category.nameList.value.push(response.shopBrand.category.nameList[i]);
                        $scope.category.pathList.value.push(response.shopBrand.category.pathList[i]);
                        $scope.category.urlList.value.push(response.shopBrand.category.urlList[i]);

                    }

                    for(var i = 0; i < response.shopBrand.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.shopBrand.company.idList[i]);
                        $scope.company.nameList.value.push(response.shopBrand.company.nameList[i]);

                    }

                    for(var i = 0; i < response.shopBrand.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.shopBrand.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.shopBrand.company.branch.nameList[i]);

                    }

                    $scope.content.value = response.shopBrand.content;

                    var createdTimestamp = new Date(response.shopBrand.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);
                    $scope.created.timestamp.view = true;

                    $scope.created.user.id.value = response.shopBrand.created.user.id;
                    $scope.created.user.username.value = response.shopBrand.created.user.username;
                    $scope.description.value = response.shopBrand.description;
                    $scope.dislike.value = library.numeral.initializeSeparator(response.shopBrand.dislike, true);

                    if(response.shopBrand.imageList.length > 0) {

                        $scope.imageList.value = response.shopBrand.imageList;
                        $scope.imageList.upload.file = response.shopBrand.imageList;
                        $scope.imageList.upload.view = true;

                    }

                    $scope.id.value = response.shopBrand.id;
                    $scope.like.value = library.numeral.initializeSeparator(response.shopBrand.like, true);
                    $scope.meta.description.value = response.shopBrand.meta.description;
                    $scope.meta.keyword.value = response.shopBrand.meta.keyword;
                    $scope.meta.title.value = response.shopBrand.meta.title;

                    var modifiedTimestamp = new Date(response.shopBrand.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);
                    $scope.modified.timestamp.view = true;

                    $scope.modified.user.id.value = response.shopBrand.modified.user.id;
                    $scope.modified.user.username.value = response.shopBrand.modified.user.username;
                    $scope.name.value = response.shopBrand.name;
                    $scope.og.description.value = response.shopBrand.og.description;
                    $scope.og.title.value = response.shopBrand.og.title;
                    $scope.rate.value = library.numeral.initializeSeparator(response.shopBrand.rate, true);
                    $scope.sequence.value = library.numeral.initializeSeparator(response.shopBrand.sequence, true);

                    if(response.shopBrand.thumbnailList.length > 0) {

                        $scope.thumbnailList.value = response.shopBrand.thumbnailList;
                        $scope.thumbnailList.upload.file = response.shopBrand.thumbnailList;
                        $scope.thumbnailList.upload.view = true;

                    }

                    $scope.url.value = response.shopBrand.url;
                    $scope.view.value = library.numeral.initializeSeparator(response.shopBrand.view, true);

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

                    if(response.shopBrand.id != null) {

                        if(value == response.shopBrand.status) {

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
            "rate": 0,
            "sequence": 0,
            "view": 0
        };

        $scope.dislike.value = library.numeral.initializeSeparator($scope.dislike.value, true);
        $scope.like.value = library.numeral.initializeSeparator($scope.like.value, true);
        $scope.rate.value = library.numeral.initializeSeparator($scope.rate.value, true);
        $scope.sequence.value = library.numeral.initializeSeparator($scope.sequence.value, true);
        $scope.view.value = library.numeral.initializeSeparator($scope.view.value, true);

        result.dislike = $scope.dislike.value.replace(/[^0-9]/g, "");
        result.like = $scope.like.value.replace(/[^0-9]/g, "");
        result.rate = $scope.rate.value.replace(/[^0-9.]/g, "");
        result.sequence = $scope.sequence.value.replace(/[^0-9]/g, "");
        result.view = $scope.view.value.replace(/[^0-9]/g, "");

        return result;

    }


    $scope.initializePagination = function() {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/brand/initialize-pagination"
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
                    "content": tinyMCE.get("tinymce-2").getContent(),
                    "description": tinyMCE.get("tinymce-1").getContent(),
                    "dislike": input.dislike,
                    "imageList": $scope.imageList.value,
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
                    "path": $scope.path.value,
                    "rate": input.rate,
                    "sequence": input.sequence,
                    "status": $scope.status.selected.value,
                    "thumbnailList": $scope.thumbnailList.value,
                    "url": $scope.url.value,
                    "view": input.view
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/brand/insert"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/brand/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.shopBrand.id != null) {

                    for(var i = 0; i < response.shopBrand.category.idList.length; i++) {

                        $scope.category.idList.value.push(response.shopBrand.category.idList[i]);
                        $scope.category.nameList.value.push(response.shopBrand.category.nameList[i]);
                        $scope.category.pathList.value.push(response.shopBrand.category.pathList[i]);
                        $scope.category.urlList.value.push(response.shopBrand.category.urlList[i]);

                    }

                    for(var i = 0; i < response.shopBrand.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.shopBrand.company.idList[i]);
                        $scope.company.nameList.value.push(response.shopBrand.company.nameList[i]);

                    }

                    for(var i = 0; i < response.shopBrand.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.shopBrand.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.shopBrand.company.branch.nameList[i]);

                    }

                    $scope.content.value = response.shopBrand.content;

                    var createdTimestamp = new Date(response.shopBrand.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);

                    $scope.created.user.id.value = response.shopBrand.created.user.id;
                    $scope.created.user.username.value = response.shopBrand.created.user.username;
                    $scope.description.value = response.shopBrand.description;
                    $scope.dislike.value = library.numeral.initializeSeparator(response.shopBrand.dislike, true);

                    if(response.shopBrand.imageList.length > 0) {

                        angular.forEach(response.shopBrand.imageList, function(value, key) {

                            $scope.imageList.upload.file.push(document.getElementById("global").getAttribute("data-image-url") + "/shop/brand/" + value);

                        });

                        $scope.imageList.upload.view = true;

                    }

                    $scope.id.value = response.shopBrand.id;
                    $scope.like.value = library.numeral.initializeSeparator(response.shopBrand.like, true);
                    $scope.meta.description.value = response.shopBrand.meta.description;
                    $scope.meta.keyword.value = response.shopBrand.meta.keyword;
                    $scope.meta.title.value = response.shopBrand.meta.title;

                    var modifiedTimestamp = new Date(response.shopBrand.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);

                    $scope.modified.user.id.value = response.shopBrand.modified.user.id;
                    $scope.modified.user.username.value = response.shopBrand.modified.user.username;
                    $scope.name.value = response.shopBrand.name;
                    $scope.og.description.value = response.shopBrand.og.description;
                    $scope.og.title.value = response.shopBrand.og.title;
                    $scope.rate.value = library.numeral.initializeSeparator(response.shopBrand.rate, true);
                    $scope.sequence.value = library.numeral.initializeSeparator(response.shopBrand.sequence, true);
                    $scope.status.value = $scope.camelCaseToStandardCase(response.shopBrand.status);

                    if(response.shopBrand.thumbnailList.length > 0) {

                        angular.forEach(response.shopBrand.thumbnailList, function(value, key) {

                            $scope.thumbnailList.upload.file.push(document.getElementById("global").getAttribute("data-image-url") + "/shop/brand/thumbnail/" + value);

                        });

                        $scope.thumbnailList.upload.view = true;

                    }

                    $scope.url.value = response.shopBrand.url;
                    $scope.view.value = library.numeral.initializeSeparator(response.shopBrand.view, true);

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

                    $scope.categoryOptionHierarchy(response.shopCategoryList, 0, 0);

                }

                $scope.popup.view = true;
                $scope.popup.shopBrand = true;

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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/brand/remove-filter-pagination"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/brand/set-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/shop/brand/";

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


    $scope.uploadImageList = function() {

        $scope.loading.view = true;

        var formData = new FormData();

        angular.forEach($scope.files, function(value) {

            formData.append("file", value);

        });

        var rest = {
            "data": formData,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/brand/upload-image-list"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/shop/brand/upload-thumbnail-list"
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


app.directive("shopBrandDetail", function() {


    var shopBrand = {};


    shopBrand.templateUrl = document.getElementById("global").getAttribute("data-base-url") + "/html/shop-brand-detail-popup.html";


    return shopBrand;


});


app.directive("shopBrandImageListPreview", function() {


    var shopBrand = {};


    shopBrand.template = "<span ng-repeat=\"data in imageList.upload.file\">" +
        "<img class=\"responsive\" ng-src=\"" + document.getElementById("global").getAttribute("data-image-url") + "/shop/brand/{{data}}\" />" +
        "</span>";


    return shopBrand;


});


app.directive("shopBrandThumbnailListPreview", function() {


    var shopBrand = {};


    shopBrand.template = "<span ng-repeat=\"data in thumbnailList.upload.file\">" +
        "<img class=\"responsive\" ng-src=\"" + document.getElementById("global").getAttribute("data-image-url") + "/shop/brand/thumbnail/{{data}}\" />" +
        "</span>";


    return shopBrand;


});
