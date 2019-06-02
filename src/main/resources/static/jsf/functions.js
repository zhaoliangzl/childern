/*global  jQuery */
/* Contents
// ------------------------------------------------>
	1.  Mobile Menu
	2.  Header Affix
	3.  Background Image
	4.  Magnific Popup
	5.  Countdown Date
	6.  Counter Up
	7.  Twitter Feed
	8.  Flickr Stream
	9.  Testimonial Carousel
	10. Slide Range
	11. Product Image SLider
	12. Product Carousel
	13. Client Carousel
	14. Hero Slider

*/
(function($) {
    "use strict";

    /* ------------------  mobile menu ------------------ */

    var $dropToggle = $("ul.dropdown-menu [data-toggle=dropdown]"),
        $module = $(".module");
    $dropToggle.on("click", function(event) {
        event.preventDefault();
        event.stopPropagation();
        $(this).parent().siblings().removeClass("open");
        $(this).parent().toggleClass("open");
    });
     $module.on("click",function() {
        $(this).toggleClass("toggle-module");
    });
    $module.find("input.form-control", ".btn", ".cancel").on("click",function(e) {
        e.stopPropagation();
    });

    /* ------------------ Header Affix ------------------ */

    var $navAffix = $(".header nav");
    $navAffix.affix({
        offset: {
            top: 50
        }
    });

    /* ------------------  Background Image ------------------ */

    var $bgSection = $(".bg-section");
    var $bgPattern = $(".bg-pattern");
    var $colBg = $(".col-bg");

    $bgSection.each(function() {
        var bgSrc = $(this).children("img").attr("src");
        var bgUrl = 'url(' + bgSrc + ')';
        $(this).parent().css("backgroundImage", bgUrl);
        $(this).parent().addClass("bg-section");
        $(this).remove();
    });

    $bgPattern.each(function() {
        var bgSrc = $(this).children("img").attr("src");
        var bgUrl = 'url(' + bgSrc + ')';
        $(this).parent().css("backgroundImage", bgUrl);
        $(this).parent().addClass("bg-pattern");
        $(this).remove();
    });

    $colBg.each(function() {
        var bgSrc = $(this).children("img").attr("src");
        var bgUrl = 'url(' + bgSrc + ')';
        $(this).parent().css("backgroundImage", bgUrl);
        $(this).parent().addClass("col-bg");
        $(this).remove();
    });


    /* ------------------  Magnific Popup ------------------ */

    $('.popup-video,.popup-gmaps').magnificPopup({
        disableOn: 700,
        mainClass: 'mfp-fade',
        removalDelay: 0,
        preloader: false,
        fixedContentPos: false,
        type: 'iframe',
        iframe: {
            markup: '<div class="mfp-iframe-scaler">' +
                '<div class="mfp-close"></div>' +
                '<iframe class="mfp-iframe" frameborder="0" allowfullscreen></iframe>' +
                '</div>',
            patterns: {
                youtube: {
                    index: 'youtube.com/',
                    id: 'v=',
                    src: '//www.youtube.com/embed/%id%?autoplay=1'
                }
            },
            srcAction: 'iframe_src',
        }
    });


    var $imgPopup = $(".img-popup");
    $imgPopup.magnificPopup({
        type: "image"
    });


    /* ------------------ Countdown Date ------------------ */

    var newDate = new Date(2017, 10, 31),
        $countDown = $("#countdown");
    $countDown.countdown({
        until: newDate,
        format: "smSMHD"
    });
    /* ------------------  Counter Up ------------------ */
    $(".counter").counterUp({
        delay: 10,
        time: 1000
    });

    /* ------------------  Twitter Feed ------------------ */

    var twitterFeed = $('#twitter-feed'),
        twitterID = '721372281637888000'; // Your Twitter Widget Id Here

    if (twitterFeed.length > 0) {
        /* Get Last 2 Tweets */
        var twitterConfig = {
            "id": twitterID,
            "domId": 'twitter-feed',
            "maxTweets": 2,
            "showUser": false,
            "showTime": true,
            "showRetweet": false,
            "showInteraction": false,
            "enableLinks": true,
            "customCallback": handleTweets,
            "dateFunction": momentDateFormatter,
        };

        function handleTweets(tweets) {
            var x = tweets.length;
            var n = 0;
            var element = document.getElementById('twitter-feed');
            var html = '<ul class="list-unstyled mb-0">';
            while (n < x) {
                html += '<li>' + tweets[n] + '</li>';
                n++;
            }
            html += '</ul>';
            element.innerHTML = html;
        }

        function momentDateFormatter(date, dateString) {
            return moment(dateString).fromNow();
        }
        twitterFetcher.fetch(twitterConfig);
    }

    /* ------------------  Flickr Stream ------------------ */

    var flickrID = '52617155@N08'; // Your Flickr Account Id Here
    var $flikrDiv = $('#flickr-feed');
    $flikrDiv.jflickrfeed({
        limit: 10,
        qstrings: {
            id: flickrID
        },
        itemTemplate: '<a class="flickr-item" href="{{image}}" title="{{title}}"><img src="{{image_s}}" alt="{{title}}" /></a>'
    }, function(data) {
        $('.flickr-item').magnificPopup({
            type: 'image',
            gallery: {
                enabled: true
            }
        });
    });

    /* ------------------ Testimonial Carousel ------------------ */

    var $testimonialCarousel = $(".testimonial-carousel");
    $testimonialCarousel.slick({
        dots: true,
        infinite: true,
        speed: 600,
        autoplay: false,
        autoplaySpeed: 2000,
        slidesToShow: 3,
        slidesToScroll: 3,
        arrows: false,
        responsive: [{
                breakpoint: 1024,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 3,
                    infinite: true,
                    dots: true
                }
            },
            {
                breakpoint: 991,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2
                }
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1
                }
            }
        ]
    });

    /* ------------------ Slide Range ------------------ */

    var $sliderRange = $("#slider-range"),
        $sliderAmount = $("#amount");
    $sliderRange.slider({
        range: true,
        min: 0,
        max: 500,
        values: [50, 300],
        slide: function(event, ui) {
            $sliderAmount.val("$" + ui.values[0] + " - $" + ui.values[1]);
        }
    });
    $sliderAmount.val("$" + $sliderRange.slider("values", 0) + " - $" + $sliderRange.slider("values", 1));

    /* ------------------ Product Image SLider ------------------ */
    $('.product-img-slider').slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: false,
        fade: true,
        asNavFor: '.product-img-nav',
        infinite: true,

    });
    $('.product-img-nav').slick({
        slidesToShow: 4,
        slidesToScroll: 2,
        asNavFor: '.product-img-slider',
        dots: false,
        arrows: true,
        prevArrow: '<div class="arrows arrow-prev"><i class="fa fa-angle-left"></i></div>',
        nextArrow: '<div class="arrows arrow-next"><i class="fa fa-angle-right"></i></div>',
        centerMode: false,
        focusOnSelect: true,
        infinite: true,
        responsive: [{
            breakpoint: 1280,
            settings: {
                slidesToShow: 4
            }
        }, {
            breakpoint: 768,
            settings: {
                slidesToShow: 2
            }
        }]
    });

    /* ------------------ Product Carousel ------------------ */
    var $productCarousel = $(".product-carousel");
    $productCarousel.slick({
        dots: true,
        infinite: true,
        speed: 600,
        autoplay: false,
        autoplaySpeed: 2000,
        slidesToShow: 4,
        slidesToScroll: 4,
        variableWidth: false,
        arrows: false,
        responsive: [{
                breakpoint: 1024,
                settings: {
                    slidesToShow: 4,
                    slidesToScroll: 4,
                    infinite: true,
                    dots: true
                }
            },
            {
                breakpoint: 991,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 3
                }
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2
                }
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1
                }
            }
        ]
    });

    /* ------------------ Client Carousel ------------------ */
    var $clientCarousel = $(".client-carousel");
    $clientCarousel.slick({
        dots: false,
        infinite: true,
        autoplay: true,
        autoplaySpeed: 4000,
        slidesToShow: 6,
        slidesToScroll: 6,
        variableWidth: false,
        arrows: false,
        responsive: [{
                breakpoint: 1024,
                settings: {
                    slidesToShow: 6,
                    slidesToScroll: 6,
                    infinite: true,
                    dots: true
                }
            },
            {
                breakpoint: 991,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 3
                }
            },
            {
                breakpoint: 800,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2
                }
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1
                }
            }
        ]
    });

    /* ------------------ Hero Slider ------------------ */
    var $heroSlider = $(".hero-slider");
    $heroSlider.slick({
        dots: true,
        infinite: true,
        speed: 600,
        autoplay: false,
        autoplaySpeed: 2000,
        slidesToShow: 1,
        slidesToScroll: 1,
        variableWidth: false,
        arrows: true,
        prevArrow: '<div class="arrows arrow-prev"><i class="fa fa-angle-left"></i></div>',
        nextArrow: '<div class="arrows arrow-next"><i class="fa fa-angle-right"></i></div>',

    });
}(jQuery));