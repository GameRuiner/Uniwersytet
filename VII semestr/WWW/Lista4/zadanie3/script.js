$(function(){
   
   $('img').on('click', function () {
     if ($( this ).data('marked')){
        $( this ).removeClass("red-border");
        $( this ).data( 'marked', false );
     } else {
        $( this ).addClass("red-border");
        $( this ).data( 'marked', true );
     }    
   });
   
   $('#select_all').on('click', function () {
      $('img').addClass("red-border");
      $('img').data( 'marked', true );
   });
   
   $('#log_button').on('click', function () {
      let logString = ""
      
      $('img').filter(function() {
         return $( this ).data('marked');
                    }).each(function () {
                        logString+=" "+$( this ).attr('id');
                      });
      $('#log_input').val(logString);
   });
   
});