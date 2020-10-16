document.addEventListener("DOMContentLoaded", function(event) {


    tinymce.init({
        "branding": false,
        "convert_urls": true,
        "height": 300,
        "images_upload_url": document.getElementById("global").getAttribute("data-base-url") + "/blog/category/upload-tinymce",
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
        "images_upload_url": document.getElementById("global").getAttribute("data-base-url") + "/blog/category/upload-tinymce",
        "image_advtab": true,
        "plugins": "preview searchreplace autolink directionality visualblocks visualchars image link media template codesample table charmap hr pagebreak nonbreaking anchor toc insertdatetime advlist lists textcolor wordcount imagetools contextmenu colorpicker textpattern",
        "relative_urls": false,
        "remove_script_host": false,
        "selector": "#tinymce-2",
        "toolbar1": "formatselect | bold italic strikethrough forecolor backcolor | link | alignleft aligncenter alignright alignjustify  | numlist bullist outdent indent  | removeformat"
    });


});


app.controller("blogCategory", ["$scope", "$window", "global", function($scope, $window, global) {


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

    $scope.parent = {
        "option": [
            {"icon": "", "id": "", "name": "Parent", "path": "", "url": ""},
            {"icon": "", "id": "0", "name": "Root", "path": "", "url": ""}
        ],
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
    };

    $scope.parent.selected = $scope.parent.option[0];

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
        "dislike": false,
        "like": false,
        "name": false,
        "parent": false,
        "rate": false,
        "sequence": false,
        "url": false,
        "view": false
    });


    $scope.checkData = function() {

        $scope.checkCompany();

        $scope.checkCompanyBranch();

        $scope.checkDislike();

        $scope.checkLike();

        $scope.checkName();

        $scope.checkParent();

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


    $scope.checkParent = function() {

        if($scope.parent.selected.id == "") {

            $scope.parent.response.value = "Please select parent";
            $scope.parent.response.class = "error";
            $scope.parent.response.view = true;
            $scope.valid.parent = false;

        } else {

            $scope.parent.response.view = false;
            $scope.valid.parent = true;

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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/category/delete"
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
                    "content": tinyMCE.get("tinymce-1").getContent(),
                    "description": tinyMCE.get("tinymce-2").getContent(),
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
                    "parent": {
                        "id": $scope.parent.selected.id,
                        "name": $scope.parent.selected.name,
                        "path": $scope.parent.selected.path,
                        "url": $scope.parent.selected.url
                    },
                    "rate": input.rate,
                    "sequence": input.sequence,
                    "status": $scope.status.selected.value,
                    "thumbnailList": $scope.thumbnailList.value,
                    "url": $scope.url.value,
                    "view": input.view
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/category/update"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/category/filter-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/blog/category/";

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

        document.getElementsByClassName("blog-category-image-list")[0].click();

    }


    $scope.forceUploadThumbnailList = function() {

        document.getElementsByClassName("blog-category-thumbnail-list")[0].click();

    }


    $scope.goToPage = function(event) {

        if(event.which == 13) {

            $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/blog/category/page-" + $scope.site.page.number + "/";

        }

        event.preventDefault();

    }


    $scope.initializeData = function(id) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/category/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.blogCategory.id != null) {

                    for(var i = 0; i < response.blogCategory.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.blogCategory.company.idList[i]);
                        $scope.company.nameList.value.push(response.blogCategory.company.nameList[i]);

                    }

                    for(var i = 0; i < response.blogCategory.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.blogCategory.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.blogCategory.company.branch.nameList[i]);

                    }

                    $scope.content.value = response.blogCategory.content;

                    var createdTimestamp = new Date(response.blogCategory.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);
                    $scope.created.timestamp.view = true;

                    $scope.created.user.id.value = response.blogCategory.created.user.id;
                    $scope.created.user.username.value = response.blogCategory.created.user.username;
                    $scope.description.value = response.blogCategory.description;
                    $scope.dislike.value = library.numeral.initializeSeparator(response.blogCategory.dislike, true);

                    if(response.blogCategory.imageList.length > 0) {

                        $scope.imageList.value = response.blogCategory.imageList;
                        $scope.imageList.upload.file = response.blogCategory.imageList;
                        $scope.imageList.upload.view = true;

                    }

                    $scope.id.value = response.blogCategory.id;
                    $scope.like.value = library.numeral.initializeSeparator(response.blogCategory.like, true);
                    $scope.meta.description.value = response.blogCategory.meta.description;
                    $scope.meta.keyword.value = response.blogCategory.meta.keyword;
                    $scope.meta.title.value = response.blogCategory.meta.title;

                    var modifiedTimestamp = new Date(response.blogCategory.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);
                    $scope.modified.timestamp.view = true;

                    $scope.modified.user.id.value = response.blogCategory.modified.user.id;
                    $scope.modified.user.username.value = response.blogCategory.modified.user.username;
                    $scope.name.value = response.blogCategory.name;
                    $scope.og.description.value = response.blogCategory.og.description;
                    $scope.og.title.value = response.blogCategory.og.title;
                    $scope.rate.value = library.numeral.initializeSeparator(response.blogCategory.rate, true);
                    $scope.sequence.value = library.numeral.initializeSeparator(response.blogCategory.sequence, true);

                    if(response.blogCategory.thumbnailList.length > 0) {

                        $scope.thumbnailList.value = response.blogCategory.thumbnailList;
                        $scope.thumbnailList.upload.file = response.blogCategory.thumbnailList;
                        $scope.thumbnailList.upload.view = true;

                    }

                    $scope.url.value = response.blogCategory.url;
                    $scope.view.value = library.numeral.initializeSeparator(response.blogCategory.view, true);

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

                    if(response.blogCategory.id != null) {

                        if(value == response.blogCategory.status) {

                            $scope.status.selected = $scope.status.option[(key + 1)];

                        }

                    }

                });

                var selectedId = "";

                if(response.blogCategory.id != null) {

                    if(response.blogCategory.parent.id == 0) {

                        $scope.parent.selected = $scope.parent.option[1];

                    } else {

                        selectedId = response.blogCategory.parent.id;

                    }

                }

                $scope.parentOptionHierarchy(response.blogCategoryList, 0, 0, $scope.id.value, selectedId);

            } else {

                $scope.response.class = "error";
                $scope.response.message = response.response;
                $scope.response.view = true;

            }

            $scope.loading.view = false;

            $scope.hideResponse();

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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/category/initialize-pagination"
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

                    angular.forEach($scope.filter.status.option, function(value, key) {

                        if(value.value == response.filter.status[1]) {

                            $scope.filter.status.selected = $scope.filter.status.option[key];

                            return false;

                        }

                    });

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
                    "content": tinyMCE.get("tinymce-1").getContent(),
                    "description": tinyMCE.get("tinymce-2").getContent(),
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
                    "parent": {
                        "id": $scope.parent.selected.id,
                        "name": $scope.parent.selected.name,
                        "path": $scope.parent.selected.path,
                        "url": $scope.parent.selected.url
                    },
                    "path": $scope.path.value,
                    "rate": input.rate,
                    "sequence": input.sequence,
                    "status": $scope.status.selected.value,
                    "thumbnailList": $scope.thumbnailList.value,
                    "url": $scope.url.value,
                    "view": input.view
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/category/insert"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/category/initialize-data"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.blogCategory.id != null) {

                    for(var i = 0; i < response.blogCategory.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.blogCategory.company.idList[i]);
                        $scope.company.nameList.value.push(response.blogCategory.company.nameList[i]);

                    }

                    for(var i = 0; i < response.blogCategory.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.blogCategory.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.blogCategory.company.branch.nameList[i]);

                    }

                    $scope.content.value = response.blogCategory.content;

                    var createdTimestamp = new Date(response.blogCategory.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);
                    $scope.created.timestamp.view = true;

                    $scope.created.user.id.value = response.blogCategory.created.user.id;
                    $scope.created.user.username.value = response.blogCategory.created.user.username;
                    $scope.description.value = response.blogCategory.description;
                    $scope.dislike.value = library.numeral.initializeSeparator(response.blogCategory.dislike, true);

                    if(response.blogCategory.imageList.length > 0) {

                        angular.forEach(response.blogCategory.imageList, function(value, key) {

                            $scope.imageList.upload.file.push(document.getElementById("global").getAttribute("data-image-url") + "/blog/category/" + value);

                        });

                        $scope.imageList.upload.view = true;

                    }

                    $scope.id.value = response.blogCategory.id;
                    $scope.like.value = library.numeral.initializeSeparator(response.blogCategory.like, true);
                    $scope.meta.description.value = response.blogCategory.meta.description;
                    $scope.meta.keyword.value = response.blogCategory.meta.keyword;
                    $scope.meta.title.value = response.blogCategory.meta.title;

                    var modifiedTimestamp = new Date(response.blogCategory.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);
                    $scope.modified.timestamp.view = true;

                    $scope.modified.user.id.value = response.blogCategory.modified.user.id;
                    $scope.modified.user.username.value = response.blogCategory.modified.user.username;
                    $scope.name.value = response.blogCategory.name;
                    $scope.og.description.value = response.blogCategory.og.description;
                    $scope.og.title.value = response.blogCategory.og.title;
                    $scope.parent.value = response.blogCategory.parent.name;
                    $scope.rate.value = library.numeral.initializeSeparator(response.blogCategory.rate, true);
                    $scope.sequence.value = library.numeral.initializeSeparator(response.blogCategory.sequence, true);
                    $scope.status.value = $scope.camelCaseToStandardCase(response.blogCategory.status);

                    if(response.blogCategory.thumbnailList.length > 0) {

                        angular.forEach(response.blogCategory.thumbnailList, function(value, key) {

                            $scope.thumbnailList.upload.file.push(document.getElementById("global").getAttribute("data-image-url") + "/blog/category/thumbnail/" + value);

                        });

                        $scope.thumbnailList.upload.view = true;

                    }

                    $scope.url.value = response.blogCategory.url;
                    $scope.view.value = library.numeral.initializeSeparator(response.blogCategory.view, true);

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

                }

                $scope.popup.view = true;
                $scope.popup.blogCategory = true;

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


    $scope.parentOptionHierarchy = function(list, parentId, level, currentId, selectedId) {

        angular.forEach(list, function(value, key) {

            if(value.parent.id == parentId) {

                var icon = "";

                for(var i = 0; i <= level; i++) {

                    icon += "<i class=\"double-arrow-right-white square-10 margin-right-5\"></i>";

                }

                $scope.parent.option.push({
                    "icon": icon,
                    "id": value.id,
                    "name": value.name,
                    "path": value.path,
                    "url": value.url
                });

                if(value.id == selectedId) {

                    $scope.parent.selected = $scope.parent.option[$scope.parent.option.length - 1];

                }

                level++;

                $scope.parentOptionHierarchy(list, value.id, level, currentId, selectedId);

                level--;

            }

        });

    }


    $scope.removeFilterPagination = function(event) {

        $scope.loading.view = true;

        var rest = {
            "data": "",
            "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/category/remove-filter-pagination"
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
            "data": $scope.site.page.number,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/category/set-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/blog/category/";

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


    $scope.uploadImageList = function(index) {

        $scope.loading.view = true;

        var formData = new FormData();

        angular.forEach($scope.files, function(value) {

            formData.append("file", value);

        });

        var rest = {
            "data": formData,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/category/upload-image-list"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/category/upload-thumbnail-list"
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


app.directive("blogCategoryDetail", function() {


    var blogCategory = {};


    blogCategory.templateUrl = document.getElementById("global").getAttribute("data-base-url") + "/html/blog-category-detail-popup.html";


    return blogCategory;


});


app.directive("blogCategoryImageListPreview", function() {


    var blogCategory = {};


    blogCategory.template = "<span ng-repeat=\"data in imageList.upload.file\">" +
        "<img class=\"responsive\" ng-src=\"" + document.getElementById("global").getAttribute("data-image-url") + "/blog/category/{{data}}\" />" +
        "</span>";


    return blogCategory;


});


app.directive("blogCategoryThumbnailListPreview", function() {


    var blogCategory = {};


    blogCategory.template = "<span ng-repeat=\"data in thumbnailList.upload.file\">" +
        "<img class=\"responsive\" ng-src=\"" + document.getElementById("global").getAttribute("data-image-url") + "/blog/category/thumbnail/{{data}}\" />" +
        "</span>";


    return blogCategory;


});
