var sbg = {

    loadingImage: null,

    currentPosition: null,

    init: function (hierarchy) {
        sbg.loadingImage = new Image();
        sbg.loadingImage.src = "sbg/hex-loader2.gif";

        sbg.currentPosition = new Position(hierarchy, null);
        sbg.displayCurrentPosition();
    },

    createImageElement: function (name) {
        var a = document.createElement("a");
        a.rel = "group";
        a.href = "i?image=" + name;
        a.title = name;
        a.className = "grid-item";

        //a.appendChild(sbg.loadingImage);

        var img = new Image();
        img.onload = function () {
            $(a).empty();
            a.appendChild(img);
            $(img).hide(0);
            $(img).fadeIn(1000);
        }
        img.src = "i?image=" + name;
        img.className = "mini";
        return a;
    },

    createDirElement: function (position, text) {
        var div = document.createElement("div");
        if (typeof(text) == 'undefined') {
            var name = position.dir.name;
            div.innerText = name.substring(name.lastIndexOf("/") + 1);
        } else {
            div.innerText = text;
        }
        $(div).data("position", position);
        div.addEventListener("click", sbg.cd);
        return div;
    },

    cd: function (event) {
        sbg.currentPosition = $(event.target).data("position");
        sbg.displayCurrentPosition()
    },

    onLoadImage: function (event) {
        console.log("sjkdfgb")

    },

    displayCurrentPosition: function () {
        $("#images").empty();
        $("#dirs").empty();
        $("#header").empty();

        $("#header").append(sbg.currentPosition.dir.name);


        var waitForParentDelay = 0;
        var delay = 250;
        var duration = 500;

        if (sbg.currentPosition.parentPosition != null) {
            var dirElement = sbg.createDirElement(sbg.currentPosition.parentPosition, '..');
            $("#dirs").append(dirElement);
            $(dirElement).hide(0);
            $(dirElement).delay(delay).slideDown(duration);
            waitForParentDelay = delay;
        }
        for (i in sbg.currentPosition.dir.dirs) {
            var subDir = sbg.currentPosition.dir.dirs[i];
            let dirElement = sbg.createDirElement(new Position(subDir, sbg.currentPosition));
            $("#dirs").append(dirElement);
            $(dirElement).hide(0);
            $(dirElement).delay(waitForParentDelay + delay * i).slideDown(duration);
        }

        for (i in sbg.currentPosition.dir.images) {
            var image = sbg.currentPosition.dir.images[i];
            var imageElement = sbg.createImageElement(image);
            $("#images").append(imageElement);
        }

        var $grid = $('.grid').packery({
            itemSelector: '.grid-item',
            percentPosition: true
        });

        $grid.imagesLoaded().progress(function () {
            $grid.packery('shiftLayout');
        });
        /*

         var image = sbg.currentPosition.dir.images[0];
         var imageElement = sbg.createImageElement(image);
         $("#images").append(imageElement);



         var currentImage = 1;
         var lastImage = sbg.currentPosition.dir.images.length - 1;


         var cnt = $("img").length;
         $("img").one("load", function() {
         cnt--;

         // If all images are loaded, init Packery
         if (cnt === 0)
         {
         $(".js-packery").packery({
         itemSelector: "packery-item"
         });
         }

         }).each(function() {
         if(this.complete) $(this).load();
         });


         $grid.imagesLoaded().progress( function() {
         if (currentImage <= lastImage) {
         var image = sbg.currentPosition.dir.images[currentImage++];
         var imageElement = sbg.createImageElement(image);
         //$("#images").append(imageElement);

         // append elements to container
         $grid.append(imageElement)
         // add and lay out newly appended elements
         .packery('appended', imageElement);
         }
         });

         /*
         for (i in sbg.currentPosition.dir.images) {
         var image = sbg.currentPosition.dir.images[i];
         var imageElement = sbg.createImageElement(image);
         $("#images").append(imageElement);
         //$(imageElement).hide(0);
         //$(imageElement).delay(delay * i).fadeIn(duration);
         }


         */
        /*
         var $grid = $('.grid').packery({
         itemSelector: '.grid-item'
         });

         $('.append-button').on( 'click', function() {
         // create new item elements
         var $items = getItemElement().add( getItemElement() ).add( getItemElement() );
         // append elements to container
         $grid.append( $items )
         // add and lay out newly appended elements
         .packery( 'appended', $items );
         });

         */
// make <div class="grid-item grid-item--width# grid-item--height#" />
        /* function getItemElement() {
         var $item = $('<div class="grid-item"></div>');
         // add width and height class
         var wRand = Math.random();
         var hRand = Math.random();
         var widthClass = wRand > 0.85 ? 'grid-item--width3' : wRand > 0.7 ? 'grid-item--width2' : '';
         var heightClass = hRand > 0.85 ? 'grid-item--height3' : hRand > 0.5 ? 'grid-item--height2' : '';
         $item.addClass( widthClass ).addClass( heightClass );
         return $item;
         }*/

        $(".grid-item").fancybox();

        /*$('.grid').masonry({
         // set itemSelector so .grid-sizer is not used in layout
         itemSelector: '.grid-item',
         // use element for option
         //columnWidth: '.grid-sizer',
         percentPosition: true
         })*/

        // init Packery


        /*
         $grid.on('click', '.grid-item', function (event) {
         // change size of item by toggling large class
         $(event.currentTarget).toggleClass('grid-item--large');
         // trigger layout after item size changes
         $grid.packery('layout');
         });
         */

// layout Packery after each image loads

    }
}

function Position(dir, parentPosition) {
    this.dir = dir;
    this.parentPosition = parentPosition;
}

$(document).ready(function () {

    $.ajax({
        url: "hierarchy"
    }).done(function (hierarchy) {
        sbg.init(hierarchy);
    });

});