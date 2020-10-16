class classAccordion {


    constructor() {

        this.setting = {
            "defaultAccordion": 0,
            "openOnlyOneAtATime": true
        };

    }


    initialize(element, setting) {

        if(!setting.hasOwnProperty("defaultAccordion")) {

            setting.defaultAccordion = this.setting.defaultAccordion;

        }

        if(!setting.hasOwnProperty("openOnlyOneAtATime")) {

            setting.openOnlyOneAtATime = this.setting.openOnlyOneAtATime;

        }

        var accordion = Array.prototype.slice.call(document.getElementsByClassName(element));

        accordion.forEach(function(value, key) {

            var navigation = Array.prototype.slice.call(value.getElementsByClassName("accordion-navigation"));
            var content = Array.prototype.slice.call(value.getElementsByClassName("accordion-content"));

            content.forEach(function(valueLevel1, keyLevel1) {

                if(keyLevel1 == setting.defaultAccordion) {

                    valueLevel1.classList.add("active");

                }

            });

            navigation.forEach(function(valueLevel1, keyLevel1) {

                if(keyLevel1 == setting.defaultAccordion) {

                    valueLevel1.classList.add("active");

                    var icon = valueLevel1.getElementsByTagName("i");

                    if(icon.length > 0) {

                        icon[0].classList.remove("plus-white");
                        icon[0].classList.add("minus-white");

                    }

                }

                valueLevel1.addEventListener("click", function(event) {

                    var index = valueLevel1.getAttribute("data-index");

                    navigation.forEach(function(valueLevel2, keyLevel2) {

                        icon = valueLevel2.getElementsByTagName("i");

                        if(valueLevel2.getAttribute("data-index") == index) {

                            if(valueLevel2.classList.value.includes("active")) {

                                valueLevel2.classList.remove("active");

                                if(icon.length > 0) {

                                    icon[0].classList.remove("minus-white");
                                    icon[0].classList.add("plus-white");

                                }

                            } else {

                                valueLevel2.classList.add("active");

                                if(icon.length > 0) {

                                    icon[0].classList.remove("plus-white");
                                    icon[0].classList.add("minus-white");

                                }

                            }

                        } else {

                            if(setting.openOnlyOneAtATime) {

                                valueLevel2.classList.remove("active");

                                if(icon.length > 0) {

                                    icon[0].classList.remove("minus-white");
                                    icon[0].classList.add("plus-white");

                                }

                            }

                        }

                    });

                    content.forEach(function(valueLevel2, keyLevel2) {

                        if(valueLevel2.getAttribute("data-index") == index) {

                            if(valueLevel2.classList.value.includes("active")) {

                                valueLevel2.classList.remove("active");

                            } else {

                                valueLevel2.classList.add("active");

                            }

                        } else {

                            if(setting.openOnlyOneAtATime) {

                                valueLevel2.classList.remove("active");

                            }

                        }

                    });

                    var layout = new classLayout();
                    layout.initializePopup();

                });

            });

        });

        var content = document.getElementById("content");

        if(content) {

            angular.element(document.getElementById("content")).scope().rebuild();

        }

    }


}


class classLayout {


    initialize() {

        this.initializePopup();

        var fullImage = document.getElementsByClassName("full-image");

        if(fullImage.length > 0) {

            this.initializeFullImageLayout();

        }

        var menu = document.getElementById("menu");

        if(menu) {

            var menuIcon = document.getElementById("menu-icon");

            if(menuIcon) {

                var height = window.innerHeight - document.getElementById("menu-icon").offsetHeight;
                menu.style.height = height + "px";

            }

        }

        menu = document.getElementsByClassName("menu");

        if(menu.length > 0) {

            var index = 0;

            for(var i = 0; i < menu.length; i++) {

                var anchor = menu[i].getElementsByTagName("a");

                if(anchor.length > 0) {

                    if(anchor[0].getAttribute("href") == window.location.pathname || anchor[0].getAttribute("href") + "/" == window.location.pathname) {

                        var span = menu[i].getElementsByTagName("span");

                        if(span.length > 0) {

                            span[0].style.width = "100%";

                        }

                        if(/child\-menu/.test(menu[i].getAttribute("class"))) {

                            index = menu[i].getAttribute("data-index");

                        }

                    }

                }

            }

            if(index > 0) {

                var childMenu = document.getElementsByClassName("child-menu");

                if(childMenu.length > 0) {

                    for(var i = 0; i < childMenu.length; i++) {

                        if(childMenu[i].getAttribute("data-index") == index) {

                            childMenu[i].style.display = "block";
                            childMenu[i].style.opacity = 1;

                            if(!/separator/.test(childMenu[i].getAttribute("class"))) {

                                childMenu[i].style.height = "40px";

                            } else {

                                childMenu[i].style.height = "3px";

                            }

                        }

                    }

                }

                var toggle = document.getElementsByClassName("menu-toggle");

                if(toggle.length > 0) {

                    for(var i = 0; i < toggle.length; i++) {

                        if(toggle[i].getAttribute("data-index") == index) {

                            toggle[i].getElementsByTagName("i")[0].style.transform = "rotate(90deg)";

                        }

                    }

                }

            }

        }

    }


    initializeFullImageLayout() {

        var image = document.getElementsByClassName("full-image")[0].getElementsByTagName("img")[0];
        var backLink = document.getElementsByClassName("back")[0];
        var height = window.innerHeight - (document.getElementById("footer").offsetHeight + parseInt(window.getComputedStyle(backLink).height));
        var pictureHeight = parseInt(window.getComputedStyle(image).height);

        if(pictureHeight < height) {

            var padding = (height - pictureHeight) / 2;
            image.style.paddingTop = padding + "px";
            backLink.style.paddingBottom = padding + "px";

        } else {

            image.removeAttribute("style");
            backLink.removeAttribute("style");

        }

    }


    initializePopup() {

        var popup = document.getElementById("popup");

        if(popup) {

            var padding = parseInt(window.getComputedStyle(popup.getElementsByClassName("wrapper")[0], null).getPropertyValue("padding-top")) * 2;
            popup.getElementsByClassName("wrapper")[0].getElementsByClassName("popup")[0].style.maxHeight = (window.innerHeight - (40 + padding)) + "px";

            var margin = (window.innerHeight - popup.getElementsByClassName("wrapper")[0].offsetHeight) / 2;
            popup.getElementsByClassName("wrapper")[0].style.marginTop = margin + "px";

        }

    }


}


class classEncryption {


    constructor() {

        this.privateKey = "MIIJQwIBADANBgkqhkiG9w0BAQEFAASCCS0wggkpAgEAAoICAQCKOqIOAByT330LxAywaTj2ZIEsYNiAq7tN1dVGBsXQMTX8m1ajaU8ORjl9bohnd0MXj6VLVhXRS4T3sBjRcz04x0LKeIYayZFMn3gdMjAxqNOo8Bg1O172b6Cbn1WlXqCHyaOnXnaicg6VUZsXaIZOnYRRGSfApLNk/BCcWchiLzfSTj0SFmeNMAzenKOcZrbozMVnKzb1sgUade+Yu1QSf3fJYPK4hJ9YLcN/YPFpCDcHdK9sOC60cHhTyxLDd8gtvbzPdtXlQJM5kKedwKv7D41KSG9XoHDL/BYJVMIkMRfNRCxm+xjl3RIiQJseovwyFyQ869mBD6trjtxpd2FCyIppOWRXl5SYCO0AFGP2ZJxDf/io5aM1QiEnBw3IyqSb2uEdXLFiODkZezPC7Vle0Kz5shMGOAT5JievGqOB43oL6qz+4thFLABcBWC6/LbNWwD/aWaACFNcpEsnqjLsCwexFIocz1eHmEGE5DbzzCRm7vNaHNvyuHiwgGCExh729IL72sNGYRgtDy798xgw/BB20KJa+lfizTTTHEGoEe7ZFrIVqTpRpDvFO1+8YhNBEo3byXO2khWggPZrM1SDhVe/CVGfE7LRiSexaeSCV4frtMD8jPIVRU7yJf63vgzHVjPbMr/6jYl+KT2lQGSZBNhrKY7msoDqUwPGF91NkwIDAQABAoICAB34rJ6gg5HEiZZ9gcrWZdV24VNABHGRyuELllzCpy2HHg+Rlu2DrfKIY3rA9q6Ie3GmYc698akZ2NDjy93OQN4IxoCkNV+liO22PhMGiNCbrJkaxgT0xJhmh8p2d9mNdP08SVHG964tUOAre5dNwKFXIhN7gtUQYR1qUie0h/BSEKZ29GLxnK5OVtwF1qvY0ZwUmlDOW/JWESk1nSfeplIEdXKipL5Bc1NpSNRbrrx5XYUuVcL/Q5E4Ob7hb0YWuL7CPyg0uhglMkbvCIj599BOBPKwVgQH860nEZggWqACTQji2UXS+AkPMBlhLMEt0cUCo4baFKUZQq/loAD2M443r1B3SSHRzVK0dH2Q52tB+/ZWvPw8YtHx2L5+OWPyJBDe7b2jAznhUy4yquH8zdJcc7p0kuGwuyA1Jr5fbN1GkRSLBOPlWmBQYzU8rCULgKVr7ToqsFarPsMOcj/fPzAFWQqBc9UnjEIg696LHYNd9GrH9hRYWbSlY0xRiHE6AJ9KkSCPe6xOtUxLoTR1xZaZ7t4NJo73SlzlhwW+xBfgAUvuWt1yJaieaAR9lcQ182JL+IXxyUw/rquxigwKb9pNOnKa4weETGfalo3WzvKrQtgUpuoPo9bAFQQd5Oo1biI+UPbmVW+yXDW25BvUdpTYLhTOIU2iuyTL5ZCdXPSBAoIBAQDJGHPIZ7ij5/VDOdCmM44CBngFSrzardTF/3mKC5FHnM0Lf3RGPmGlUc4TbxvBWKMDQSatCB4/tgg9BpLyUo7YUT5Dm6B5IO0HUuyxzTcjzRBj0OVlJf/h7AHPoCfzS+mewuleBL+0Gh/oejzbFLKyKCFGnwSA8X8D1L968L214cKH6cwvo00ayHt6JpI9bKbGZMK7QwymQ8z9aVLuM/ri1bLEkZTQftFg5IIOP5WLrOT7Kw1IwQRzraOPlZj4WqlYqpGqdMwKttIdte28od3Y3JYkcOehXOu9GhuGmqhSFmGwJKipXdinsBnXba2vrSYLaR/bf4Q3XrzNpz2Y/sEzAoIBAQCv+CKmP3A9KC1Hf3r/jmjdP6OUj9GSRZNrsGPlSMxmdS38aL+X7dDSqMDeeIH+WuXq7c7DOdmHTPFEFyNTGUTYGNuPC5+WrYzFT+e+fcHmJFgIU7LTUBPZMxq+9CwKp8CVr2X89JSRuGJw3lCX4zEBWBoNogw0y1bcdKNcCpbGIEjeTfgiPTLYY0/+XENfl1sW+15B5URZv1ZF5+f9KY2MCXo1CR9vylT9GuT2Y9JQXzicyznVWTG5CNduTV8/MYr1f+Opxw3r+xgeeZNMd4uhMhoV7wmYtpeJOC+H2BP9z0X4JvS4XJVFzSPXIU/d7G1W/ZAMVnElHRHnCO6gbAIhAoIBAQCtn7cX6LW6jqNsD5beaNTLWtp1dznndP6olRNS5PniYreGndy+aU3VdvP+Q4grP2Q/gxjpfIDhcUFbrzlgj3dVvY+Mv+mlq4/7KtXCDfT1rC/ifpIQ6uYrIaHOqfDUV062DIOA8Yiy73ttNV0lqpE+0PcqClHRIrEPlbhdZU3mo61Zu7rXxp7j6QTzSiopbo3v7T9Uf2eEhjWO9kjr8WVERE28+hUKjvQoPCMuOifcGML7OQiXME+vsNPvPLJ+e4iLTXnHOmCRP5fanyFMJFVuz8WyYOkcoSXId6ocZZu+hCSD+KLG2IgfACTPo5q6ezzFzVX8JfT5+Yn55dOBLTORAoIBAQCXEihtIblyR7PX1hH6pIBSsff16yK4uRK/OUgJ6F3S+HbqY+NYw8qrkEVEuaCghdktv8vqvxeJmPzowBSLmv4HqA+W1xvDeGuKWfdTCxQUSM9rFrBHyk/AAKfNmELXQv2LhHHCoReLowigbbwzKdFAnip/2ccw7AE+csRsmIqovb7RW47Tlka4j0p1zS/qOT7ylAtv9yyfAC1JBrecGLh0qQ5JyfM7PjGIujYncbt1VMp9kdus1k5h1L1e7P4AesAZ1BnYABRrq74hK67RzzAIy8KpYfaTLO8b1yi3L0K6e+BOEuO2A4YDzwIeB3NRy1BzhmzMqsmoWzSrbHexMgkBAoIBAEgk5E3U7PGyM+et/COW7rolfr+QuvrUH6Dixp7QVbDu6r75V4+8Z6mOjkRQk/nhtOiF5wHKGppVMbhXlrClrNj0TXrvmp/hLD74nNvHj5QmRa9McGAEmcAgmU74pvOLlzp5+/F1AqPPsc3gs16SMjkX6keS17LP3einXXwsDN7ONucJFn7fp0k2Xol6y+jHqesCFOwJpEiRlfZRWuHuXXgKkjN5zT0i0CwOjm4/7frNxYz/y3L/6Sb6k+nqHCMPelfUln4NPY9hak6T8PWUzk11zcZbkzyEwKCj/1Oj0R5KkWk6MZAiZaeq9yen38HoENTRUmsrWRhvtJTnnkfW4KQ=";
        this.publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEA37IzkriyZks/YtC5z/Vc8uQTG05+2NedcNPB4PXoSU24VUdmxwOM3yj65kYFbFMHNCRzgTaGRiL0xtg15MnDhFaKrsDxCrs0zBE3VAdKQz6IAyRDgJ/oYcoGa7eveOEdfkVbONUgfDuEgokzqt4hx7tn4/vgDoxTJCPeWQY8G74jzNylBss3uwEMKDXF1IcTcGN4sI0b2hofVA2by3NV2RcXQdcq+Ek1Cn4C4GbJ5/yrpudWkR8en1iLyrkRkM25Own4M7A4sEh+5s0Jux1TLlwVQ+qsApRbrzu4l+IZw12/RPlgYhkU6ZiWsiw4sOv3iNNp7wyDU6oDaHbzTUNYbb5ziBKhg/+DT09PQ7saDmWqMDDk+2X/6PrD9/jw2rdu555DVel0FKfI3H3QAkBOP+9Kj2KC2FfynPeZo9zbzqYhukeWO+Vs0hI2yYcnz0zuNC+HYdA4/haD4z6jmXOBq8GWkqt7WmZmBRSLfG3IJYhJLj9XUV7VH5PgJtfxTxjzAMM8W+RvO0l5Ty7kcEgolia6xaxU2aGuaP9lkytG4j6jTN8/6IkUTX6eIAQs+FuCocXCTQGloP2jpZi6NyiXV77/a7kWNPnv2NefrkuMfD6YMhL/rA/OWkIfrNmnidgcZFKsSzPsT2wRsfBRBr8qbUJ1A0dLCJHLKs7/rKfhDJsCAwEAAQ==";

    }


    rsaDecrypt(string) {

        var jsEncrypt = new JSEncrypt();
        jsEncrypt.setPrivateKey(this.privateKey);

        return jsEncrypt.decrypt(string);

    }


    rsaEncrypt(string) {

        var jsEncrypt = new JSEncrypt();
        jsEncrypt.setPublicKey(this.publicKey);

        return jsEncrypt.encrypt(string);

    }


}


class classLibrary {


    constructor() {

        this.accordion = new classAccordion();

        this.layout = new classLayout();

        this.numeral = new classNumeral();

        this.encryption = new classEncryption();

    }


}


class classNumeral {


    initializeSeparator(number, trim) {

        var result = "";

        number = number.toString().replace(/[^0-9.-]/g, "");

        var prefix = "";

        if(number.startsWith("-")) {

            prefix = "-";
            number = number.replace("-", "");

        }

        number = number.split(".");
        number[0] = number[0].split("");

        var index = 0;
        var separator = 3;

        for(var i = number[0].length - 1; i >= 0; i--) {

            if(index == separator) {

                result = "," + result;
                separator = separator + 3;

            }

            result = number[0][i] + result;

            index++;

        }

        if(number.length > 1) {

            if(trim) {

                number[1] = number[1].split("");

                var decimal = "";

                for(var i = number[1].length - 1; i >= 0; i--) {

                    if(!trim || number[1][i] != "0") {

                        decimal = number[1][i] + decimal;
                        trim = false;

                    }

                }

                if(decimal != "") {

                    result = result + "." + decimal;

                }

            } else {

                result = result + "." + number[1];

            }

        }

        result = prefix + result;

        return result;

    }


}
