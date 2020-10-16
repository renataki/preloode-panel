document.addEventListener("DOMContentLoaded", function(event) {


    tinymce.init({
        "branding": false,
        "convert_urls": true,
        "height": 300,
        "images_upload_url": document.getElementById("global").getAttribute("data-base-url") + "/blog/post/upload-tinymce",
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
        "images_upload_url": document.getElementById("global").getAttribute("data-base-url") + "/blog/post/upload-tinymce",
        "image_advtab": true,
        "plugins": "preview searchreplace autolink directionality visualblocks visualchars image link media template codesample table charmap hr pagebreak nonbreaking anchor toc insertdatetime advlist lists textcolor wordcount imagetools contextmenu colorpicker textpattern",
        "relative_urls": false,
        "remove_script_host": false,
        "selector": "#tinymce-2",
        "toolbar1": "formatselect | bold italic strikethrough forecolor backcolor | link | alignleft aligncenter alignright alignjustify  | numlist bullist outdent indent  | removeformat"
    });


});


app.controller("blogPost", ["$scope", "$window", "global", function($scope, $window, global) {


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

    $scope.content = {
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": ""
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
        "star": {
            "option": [
                {"id": "", "name": "Star"}
            ]
        },
        "status": {
            "option": [
                {"name": "Status", "value": "Unknown"}
            ]
        },
        "title": {
            "value": ""
        }
    };

    $scope.filter.category.selected = $scope.filter.category.option[0];

    $scope.filter.star.selected = $scope.filter.star.option[0];

    $scope.filter.status.selected = $scope.filter.status.option[0];

    $scope.like = {
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

    $scope.star = {
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

    $scope.title = {
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

    $scope.url = {
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
        "rate": false,
        "sequence": false,
        "title": false,
        "type": false,
        "url": false,
        "videoList": false,
        "view": false
    });

    $scope.videoList = {
        "index": [],
        "response": {
            "class": "",
            "value": "",
            "view": false
        },
        "value": []
    };

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

        $scope.checkRate();

        $scope.checkSequence();

        $scope.checkStatus();

        $scope.checkTitle();

        $scope.checkType();

        $scope.checkUrl();

        $scope.checkVideoList();

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


    $scope.checkTitle = function() {

        if($scope.title.value.length < 2) {

            $scope.title.response.value = "Please enter more than 2 characters";
            $scope.title.response.class = "error";
            $scope.title.response.view = true;
            $scope.valid.title = false;

        } else {

            $scope.title.response.view = false;
            $scope.valid.title = true;

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


    $scope.checkVideoList = function() {

        if($scope.videoList.index.length > 0) {

            for(var i = 0; i < $scope.videoList.index.length; i++) {

                if($scope.videoList.value[i].startsWith("https://")) {

                    $scope.videoList.response.value = "Please enter without https://";
                    $scope.videoList.response.class = "error";
                    $scope.videoList.response.view = true;
                    $scope.valid.videoList = false;

                } else if($scope.videoList.value[i].startsWith("http://")) {

                    $scope.videoList.response.value = "Please enter without http://";
                    $scope.videoList.response.class = "error";
                    $scope.videoList.response.view = true;
                    $scope.valid.videoList = false;

                } else if($scope.videoList.value[i].startsWith("www.")) {

                    $scope.videoList.response.value = "Please enter without www.";
                    $scope.videoList.response.class = "error";
                    $scope.videoList.response.view = true;
                    $scope.valid.videoList = false;

                } else {

                    $scope.videoList.response.view = false;
                    $scope.valid.videoList = true;

                }

            }

        } else {

            $scope.videoList.response.view = false;
            $scope.valid.videoList = true;

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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/post/delete"
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


    $scope.deleteVideoList = function(key, event) {

        $scope.video.value.splice(key, 1);

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
                    "featured": $scope.featured.value,
                    "imageList": $scope.imageList.value,
                    "like": input.like,
                    "meta": {
                        "description": $scope.meta.description.value,
                        "keyword": $scope.meta.keyword.value,
                        "title": $scope.meta.title.value
                    },
                    "og": {
                        "description": $scope.og.description.value,
                        "title": $scope.og.title.value
                    },
                    "rate": input.rate,
                    "sequence": input.sequence,
                    "star": {
                        "idList": $scope.star.idList.value,
                        "nameList": $scope.star.nameList.value,
                        "pathList": $scope.star.pathList.value,
                        "urlList": $scope.star.urlList.value
                    },
                    "status": $scope.status.selected.value,
                    "tagList": $scope.tagList.value,
                    "thumbnailList": $scope.thumbnailList.value,
                    "title": $scope.title.value,
                    "type": $scope.type.selected.value,
                    "url": $scope.url.value,
                    "videoList": $scope.videoList.value,
                    "view": input.view
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/post/update"
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
            "category.idList": ["equal", $scope.filter.category.selected.id],
            "created.timestamp": ["between", "date", filterCreatedDate[0], filterCreatedDate[1]],
            "star.id": ["equal", $scope.filter.star.selected.id],
            "status": ["equal", ""],
            "title": ["like", $scope.filter.title.value]
        };

        if($scope.filter.status.selected.value != "Unknown") {

            data["status"][1] = $scope.filter.status.selected.value;

        }

        var rest = {
            "data": data,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/post/filter-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/blog/post/";

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

        document.getElementsByClassName("blog-post-image-list")[0].click();

    }


    $scope.forceUploadThumbnailList = function() {

        document.getElementsByClassName("blog-post-thumbnail-list")[0].click();

    }


    $scope.goToPage = function(event) {

        if(event.which == 13) {

            $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/blog/post/page-" + $scope.site.page.number + "/";

        }

        event.preventDefault();

    }


    $scope.initializeData = function(id) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/post/initialize-data"
        }
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.blogPost.id != null) {

                    for(var i = 0; i < response.blogPost.category.idList.length; i++) {

                        $scope.category.idList.value.push(response.blogPost.category.idList[i]);
                        $scope.category.nameList.value.push(response.blogPost.category.nameList[i]);
                        $scope.category.pathList.value.push(response.blogPost.category.pathList[i]);
                        $scope.category.urlList.value.push(response.blogPost.category.urlList[i]);

                    }

                    for(var i = 0; i < response.blogPost.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.blogPost.company.idList[i]);
                        $scope.company.nameList.value.push(response.blogPost.company.nameList[i]);

                    }

                    for(var i = 0; i < response.blogPost.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.blogPost.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.blogPost.company.branch.nameList[i]);

                    }

                    var createdTimestamp = new Date(response.blogPost.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);
                    $scope.created.timestamp.view = true;

                    $scope.created.user.id.value = response.blogPost.created.user.id;
                    $scope.created.user.username.value = response.blogPost.created.user.username;
                    $scope.dislike.value = library.numeral.initializeSeparator(response.blogPost.dislike, true);
                    $scope.featured.value = response.blogPost.featured;
                    $scope.id.value = response.blogPost.id;

                    if(response.blogPost.imageList.length > 0) {

                        $scope.imageList.value = response.blogPost.imageList;
                        $scope.imageList.upload.file = response.blogPost.imageList;
                        $scope.imageList.upload.view = true;

                    }

                    $scope.like.value = library.numeral.initializeSeparator(response.blogPost.like, true);
                    $scope.meta.description.value = response.blogPost.meta.description;
                    $scope.meta.keyword.value = response.blogPost.meta.keyword;
                    $scope.meta.title.value = response.blogPost.meta.title;

                    var modifiedTimestamp = new Date(response.blogPost.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);
                    $scope.modified.timestamp.view = true;

                    $scope.modified.user.id.value = response.blogPost.modified.user.id;
                    $scope.modified.user.username.value = response.blogPost.modified.user.username;
                    $scope.og.description.value = response.blogPost.og.description;
                    $scope.og.title.value = response.blogPost.og.title;
                    $scope.pathList.value = response.blogPost.pathList;
                    $scope.rate.value = library.numeral.initializeSeparator(response.blogPost.rate, true);
                    $scope.sequence.value = library.numeral.initializeSeparator(response.blogPost.sequence, true);

                    for(var i = 0; i < response.blogPost.star.idList.length; i++) {

                        $scope.star.idList.value.push(response.blogPost.star.idList[i]);
                        $scope.star.nameList.value.push(response.blogPost.star.nameList[i]);
                        $scope.star.pathList.value.push(response.blogPost.star.pathList[i]);
                        $scope.star.urlList.value.push(response.blogPost.star.urlList[i]);

                    }

                    $scope.tagList.value = response.blogPost.tagList;

                    if(response.blogPost.thumbnailList.length > 0) {

                        $scope.thumbnailList.value = response.blogPost.thumbnailList;
                        $scope.thumbnailList.upload.file = response.blogPost.thumbnailList;
                        $scope.thumbnailList.upload.view = true;

                    }

                    $scope.title.value = response.blogPost.title;

                    angular.forEach($scope.type.option, function(value, key) {

                        if(value.value == response.blogPost.type) {

                            $scope.type.selected = $scope.type.option[key];

                            return false;

                        }

                    });

                    $scope.url.value = response.blogPost.url;

                    angular.forEach(response.blogPost.videoList, function(value, key) {

                        $scope.videoList.index.push(key);
                        $scope.videoList.value.push(value);

                    });

                    $scope.view.value = library.numeral.initializeSeparator(response.blogPost.view, true);

                    $scope.initializeSwitch();

                }

                angular.forEach(response.blogStarList, function(value, key) {

                    $scope.star.option.push({
                        "id": value.id,
                        "name": value.name,
                        "path": value.path,
                        "url": value.url
                    });

                });

                angular.forEach(response.blogTypeList, function(value, key) {

                    $scope.type.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.blogPost.id != null) {

                        if(value == response.blogPost.type) {

                            $scope.type.selected = $scope.type.option[(key + 1)];

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

                angular.forEach(response.statusList, function(value, key) {

                    $scope.status.option.push({
                        "name": $scope.camelCaseToStandardCase(value),
                        "value": value
                    });

                    if(response.blogPost.id != null) {

                        if(value == response.blogPost.status) {

                            $scope.status.selected = $scope.status.option[(key + 1)];

                        }

                    }

                });

                $scope.categoryOptionHierarchy(response.blogCategoryList, 0, 0);

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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/post/initialize-pagination"
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
                    $scope.filter.title.value = response.filter.title[1];

                }

                angular.forEach(response.blogStarList, function(value, key) {

                    $scope.filter.star.option.push({
                        "id": value.id,
                        "name": value.name
                    });

                    if(Object.entries(response.filter).length > 0) {

                        if(value.id == response.filter.star_id[1]) {

                            $scope.filter.star.selected = $scope.filter.star.option[(key + 1)];

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

                $scope.categoryFilterOptionHierarchy(response.blogCategoryList, 0, 0, selectedId);

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
                    "featured": $scope.featured.value,
                    "imageList": $scope.imageList.value,
                    "like": input.like,
                    "meta": {
                        "description": $scope.meta.description.value,
                        "keyword": $scope.meta.keyword.value,
                        "title": $scope.meta.title.value
                    },
                    "og": {
                        "description": $scope.og.description.value,
                        "title": $scope.og.title.value
                    },
                    "rate": input.rate,
                    "sequence": input.sequence,
                    "star": {
                        "idList": $scope.star.idList.value,
                        "nameList": $scope.star.nameList.value,
                        "pathList": $scope.star.pathList.value,
                        "urlList": $scope.star.urlList.value
                    },
                    "status": $scope.status.selected.value,
                    "tagList": $scope.tagList.value,
                    "thumbnailList": $scope.thumbnailList.value,
                    "title": $scope.title.value,
                    "type": $scope.type.selected.value,
                    "url": $scope.url.value,
                    "videoList": $scope.videoList.value,
                    "view": input.view
                },
                "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/post/insert"
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


    $scope.insertVideoList = function(event) {

        $scope.videoList.index.push($scope.videoList.index.length);
        $scope.videoList.value.push("");

        event.preventDefault();

    }


    $scope.loadDetail = function(id) {

        $scope.loading.view = true;

        var rest = {
            "data": id,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/post/initialize-data"
        }
        global.rest(rest, function(response) {

            if(response.result) {

                if(response.blogPost.id != null) {

                    for(var i = 0; i < response.blogPost.category.idList.length; i++) {

                        $scope.category.idList.value.push(response.blogPost.category.idList[i]);
                        $scope.category.nameList.value.push(response.blogPost.category.nameList[i]);
                        $scope.category.pathList.value.push(response.blogPost.category.pathList[i]);
                        $scope.category.urlList.value.push(response.blogPost.category.urlList[i]);

                    }

                    for(var i = 0; i < response.blogPost.company.idList.length; i++) {

                        $scope.company.idList.value.push(response.blogPost.company.idList[i]);
                        $scope.company.nameList.value.push(response.blogPost.company.nameList[i]);

                    }

                    for(var i = 0; i < response.blogPost.company.branch.idList.length; i++) {

                        $scope.company.branch.idList.value.push(response.blogPost.company.branch.idList[i]);
                        $scope.company.branch.nameList.value.push(response.blogPost.company.branch.nameList[i]);

                    }

                    $scope.content.value = response.blogPost.content;

                    var createdTimestamp = new Date(response.blogPost.created.timestamp);
                    $scope.created.timestamp.value = createdTimestamp.getFullYear() + "-" + ("0" + (createdTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + createdTimestamp.getDate()).slice(-2) + " " + ("0" + createdTimestamp.getHours()).slice(-2) + ":" + ("0" + createdTimestamp.getMinutes()).slice(-2) + ":" + ("0" + createdTimestamp.getSeconds()).slice(-2);

                    $scope.created.user.id.value = response.blogPost.created.user.id;
                    $scope.created.user.username.value = response.blogPost.created.user.username;
                    $scope.description.value = response.blogPost.description;
                    $scope.dislike.value = library.numeral.initializeSeparator(response.blogPost.dislike, true);
                    $scope.featured.value = response.blogPost.featured;
                    $scope.id.value = response.blogPost.id;

                    if(response.blogPost.imageList.length > 0) {

                        angular.forEach(response.blogPost.imageList, function(value, key) {

                            $scope.imageList.upload.file.push(document.getElementById("global").getAttribute("data-image-url") + "/blog/post/" + value);

                        });

                        $scope.imageList.upload.view = true;

                    }

                    $scope.like.value = library.numeral.initializeSeparator(response.blogPost.like, true);
                    $scope.meta.description.value = response.blogPost.meta.description;
                    $scope.meta.keyword.value = response.blogPost.meta.keyword;
                    $scope.meta.title.value = response.blogPost.meta.title;

                    var modifiedTimestamp = new Date(response.blogPost.modified.timestamp);
                    $scope.modified.timestamp.value = modifiedTimestamp.getFullYear() + "-" + ("0" + (modifiedTimestamp.getMonth() + 1)).slice(-2) + "-" + ("0" + modifiedTimestamp.getDate()).slice(-2) + " " + ("0" + modifiedTimestamp.getHours()).slice(-2) + ":" + ("0" + modifiedTimestamp.getMinutes()).slice(-2) + ":" + ("0" + modifiedTimestamp.getSeconds()).slice(-2);

                    $scope.modified.user.id.value = response.blogPost.modified.user.id;
                    $scope.modified.user.username.value = response.blogPost.modified.user.username;
                    $scope.og.description.value = response.blogPost.og.description;
                    $scope.og.title.value = response.blogPost.og.title;
                    $scope.pathList.value = response.blogPost.pathList;
                    $scope.rate.value = library.numeral.initializeSeparator(response.blogPost.rate, true);
                    $scope.sequence.value = library.numeral.initializeSeparator(response.blogPost.sequence, true);
                    $scope.star.value = response.blogPost.star.name;
                    $scope.status.value = $scope.camelCaseToStandardCase(response.blogPost.status);
                    $scope.tagList.value = response.blogPost.tagList;

                    if(response.blogPost.thumbnailList.length > 0) {

                        angular.forEach(response.blogPost.thumbnailList, function(value, key) {

                            $scope.thumbnailList.upload.file.push(document.getElementById("global").getAttribute("data-image-url") + "/blog/post/thumbnail/" + value);

                        });

                        $scope.thumbnailList.upload.view = true;

                    }

                    $scope.title.value = response.blogPost.title;
                    $scope.type.value = $scope.camelCaseToStandardCase(response.blogPost.type);
                    $scope.url.value = response.blogPost.url;
                    $scope.view.value = library.numeral.initializeSeparator(response.blogPost.view, true);

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

                    $scope.categoryOptionHierarchy(response.blogCategoryList, 0, 0);

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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/post/remove-filter-pagination"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/post/set-pagination"
        };
        global.rest(rest, function(response) {

            if(response.result) {

                $window.location.href = document.getElementById("global").getAttribute("data-base-url") + "/blog/post/";

            } else {

                $scope.response.class = "error";
                $scope.response.message = response.response;
                $scope.response.view = true;

            }

            $scope.hideResponse();

        });

        event.preventDefault();

    }


    $scope.starToggleCheckbox = function(star) {

        var index = $scope.star.idList.value.indexOf(star.id);

        if(index > -1) {

            $scope.star.idList.value.splice(index, 1);
            $scope.star.nameList.value.splice(index, 1);
            $scope.star.pathList.value.splice(index, 1);
            $scope.star.urlList.value.splice(index, 1);

        } else {

            $scope.star.idList.value.push(star.id);
            $scope.star.nameList.value.push(star.name);
            $scope.star.pathList.value.push(star.path);
            $scope.star.urlList.value.push(star.url);

        }

    }


    $scope.uploadImageList = function(index) {

        $scope.loading.view = true;

        var formData = new FormData();

        angular.forEach($scope.files, function(value) {

            formData.append("file", value);

        });

        var rest = {
            "data": formData,
            "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/post/upload-image-list"
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
            "url": document.getElementById("global").getAttribute("data-base-url") + "/blog/post/upload-thumbnail-list"
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


app.directive("blogPostDetail", function() {


    var blogPost = {};


    blogPost.templateUrl = document.getElementById("global").getAttribute("data-base-url") + "/html/blog-post-detail-popup.html";


    return blogPost;


});


app.directive("blogPostImageListPreview", function() {


    var blogPost = {};


    blogPost.template = "<span ng-repeat=\"imageListValue in imageList.upload.file\">" +
        "<img class=\"responsive\" ng-src=\"" + document.getElementById("global").getAttribute("data-image-url") + "/blog/post/{{imageListValue}}\" />" +
        "</span>";


    return blogPost;


});


app.directive("blogPostThumbnailListPreview", function() {


    var blogPost = {};


    blogPost.template = "<span ng-repeat=\"thumbnailListValue in thumbnailList.upload.file\">" +
        "<img class=\"responsive\" ng-src=\"" + document.getElementById("global").getAttribute("data-image-url") + "/blog/post/thumbnail/{{thumbnailListValue}}\" />" +
        "</span>";


    return blogPost;


});
