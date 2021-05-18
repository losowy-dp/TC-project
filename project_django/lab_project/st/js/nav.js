            $(window).resize(function() {
                if($(window).width() > 992)
                    $('.nav-menu').css('display','flex');
                if($(window).width() < 992)
                    $('.nav-menu').css('display','none');
            });


            bt.onclick = function() {
            if(main_menu.style.display == "none"){
                $('.nav-menu').css('display','block')
            }
            else {
                // main_menu.style.display = "none";
                $('.nav-menu').css('display','none')
            }
             
            };   