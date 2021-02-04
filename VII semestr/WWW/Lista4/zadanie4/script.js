$( function() {
    var dialog, form, 
      currentId = 0,
      idCounter = 1,
      name = $( "#name" ),
      surname = $( "#surname" ),
      city = $( "#city" ),
      postalCode = $( "#postal-code" ),
      birthDate = $( "#birth-date" ),
      allFields = $( [] ).add( name ).add( surname ).add( city ).add( postalCode ).add( birthDate ),
      tips = $( ".validateTips" );
 
    function updateTips( t ) {
      tips
        .text( t )
        .addClass( "ui-state-highlight" );
      setTimeout(function() {
        tips.removeClass( "ui-state-highlight", 1500 );
      }, 500 );
    }
 
    function checkLength( o, n, min, max ) {
      if ( o.val().length > max || o.val().length < min ) {
        o.addClass( "ui-state-error" );
        updateTips( "Length of " + n + " must be between " +
          min + " and " + max + "." );
        return false;
      } else {
        return true;
      }
    }
 
    function checkRegexp( o, regexp, n ) {
      if ( !( regexp.test( o.val() ) ) ) {
        o.addClass( "ui-state-error" );
        updateTips( n );
        return false;
      } else {
        return true;
      }
    }
 
    function addUser() {
      var valid = true;
      allFields.removeClass( "ui-state-error" );
 
      valid = valid && checkLength( name, "Imię", 3, 16 );
      valid = valid && checkLength( surname, "Nazwisko", 3, 25 );
      valid = valid && checkLength( city, "Miasto", 3, 25 );
      valid = valid && checkLength( postalCode, "Kod pocztowy", 5, 6 );
      valid = valid && checkLength( birthDate, "Data urodzenia", 8, 10 );
 
      valid = valid && checkRegexp( name, /^[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]+$/i, "Imię zawiera litery alfabetu" );
      valid = valid && checkRegexp( surname,/^[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]+$/i, "Nazwisko zawiera litery alfabetu" ); 
      valid = valid && checkRegexp( city, /^[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]+$/i, "Miasto zawiera litery alfabetu" );
      valid = valid && checkRegexp( postalCode, /^([0-9]{2})([-]*)([0-9]{3})$/, "Kod pocztowy zawiera 5 cyfr" );
 
      if ( valid ) {
        $( "#users tbody" ).append( "<tr id='tr"+idCounter+"'>" +
          "<td>" + name.val() + "</td>" +
          "<td>" + surname.val() + "</td>" +
          "<td>" + city.val() + "</td>" +          
          "<td>" + postalCode.val() + "</td>" +
          "<td>" + birthDate.val() + "</td>" +
          "<td>" + "<a href='#' id='"+idCounter+"'>Usuń</a>" + "</td>" +
        "</tr>" );
        
       
       $('#'+idCounter).on('click', function() {
          currentId = $( this ).attr('id');          
          dialogConfirm.dialog("open");
       });
       idCounter++;
       
       dialog.dialog( "close" );
      }
      return valid;
    }
 
    dialog = $( "#dialog-form" ).dialog({
      autoOpen: false,
      height: 400,
      width: 350,
      modal: true,
      buttons: {
        "Create an account": addUser,
        Cancel: function() {
          dialog.dialog( "close" );
        }
      },
      close: function() {
        form[ 0 ].reset();
        allFields.removeClass( "ui-state-error" );
      }
    });
    
    dialogConfirm = $( "#dialog-confirm" ).dialog({
      autoOpen: false,
      resizable: false,
      height: "auto",
      width: 400,
      modal: true,
      buttons: {
        "Delete all items": function() {
          $('#tr'+ currentId).remove();
          $( this ).dialog( "close" );
        },
        Cancel: function() {
          $( this ).dialog( "close" );
        }
      }
    });
 
    form = dialog.find( "form" ).on( "submit", function( event ) {
      event.preventDefault();
      addUser();
    });
 
    $( "#create-user" ).button().on( "click", function() {
      dialog.dialog( "open" );
    });
    
    
    $( "#birth-date" ).datepicker();
    $( "#birth-date" ).datepicker("option", "dateFormat", "dd-mm-yy");
    
    $( "#remove0" ).on('click', function() {
       
       currentId = 0;          
       dialogConfirm.dialog("open");
    });
  });