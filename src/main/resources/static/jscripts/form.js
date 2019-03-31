$("#signup").click(function() {
    $("#first").fadeOut("fast", function() {
    $("#second").fadeIn("fast");
    });
    });
    
    $("#signin").click(function() {
    $("#second").fadeOut("fast", function() {
    $("#first").fadeIn("fast");
    });
    });
    
    
      
             $(function() {
               $("form[name='login']").validate({
                 rules: {
                   
                   email: {
                     required: true,
                     email: true
                   },
                   password: {
                     required: true,
                     
                   }
                 },
                  messages: {
                   email: "Please enter a valid email address",
                  
                   password: {
                     required: "Please enter password",
                    
                   }
                   
                 },
                 submitHandler: function(form) {
                   form.submit();
                 }
               });
             });
             
    
    
    $(function() {
      
      $("form[name='registration']").validate({
        rules: {
          firstname: "required",
          lastname: "required",
          email: {
            required: true,
            email: true
          },
          password: {
            required: true,
            minlength: 5
          }
        },
        
        messages: {
          firstname: "Please enter your firstname",
          lastname: "Please enter your lastname",
          password: {
            required: "Please provide a password",
            minlength: "Your password must be at least 5 characters long"
          },
          email: "Please enter a valid email address"
        },
      
        submitHandler: function(form) {
          form.submit();
        }
      });
    });

    /*Function for sub-dropdown*/
$('.dropdown-menu a.dropdown-toggle').on('click', function(e) {
  if (!$(this).next().hasClass('show')) {
    $(this).parents('.dropdown-menu').first().find('.show').removeClass("show");
  }
  var $subMenu = $(this).next(".dropdown-menu");
  $subMenu.toggleClass('show');


  $(this).parents('li.nav-item.dropdown.show').on('hidden.bs.dropdown', function(e) {
    $('.dropdown-submenu .show').removeClass("show");
  });


  return false;
});


checkpass = function (validate) {
var str = document.getElementById('password').value;
if(str.length < 8 || str.length > 20)
{
document.getElementById ("message").innerHTML = "Password length must be between 8 and 20 characters!";
document.getElementById ("message").style.color="Red";
return ("Too_short_or_too_long");
}
else if (str.search(/[0-9]/) == -1) {
document.getElementById ("message").innerHTML = "Password must contain at least one numeric character!";
document.getElementById ("message").style.color="Red";
return ("No_numeric_character");
} else if (str.search(/[a-z]/) == -1) {
document.getElementById ("message").innerHTML = "Password must contain at least one small letter!";
document.getElementById ("message").style.color="Red";
return ("No_small_letter");
} else if (str.search(/[A-Z]/) == -1) {
document.getElementById ("message").innerHTML = "Password must contain at least one upper letter!";
document.getElementById ("message").style.color="Red";
return ("No_upper_letter");
} else if (str.search(/[!\@\#\$\%\^\&\*\(\)\+\:\;\.\,]/) == -1) {
document.getElementById ("message").innerHTML = "Password must contain at least one special character!";
document.getElementById ("message").style.color="Red";
return ("No_special_character");
}
document.getElementById("message").innerHTML="Password accepted!";
document.getElementById("message").style.color="Green";
return("Password_allowed")
}