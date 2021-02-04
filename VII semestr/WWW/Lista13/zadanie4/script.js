$(function() {
    $('[data-spy="scroll"]').on('activate.bs.scrollspy', function (e) {
      console.log( "scrolling" );
      let section = e.target.childNodes[0].getAttribute('href');
      if (section != "#section1") {
         $( "#mainnavbar" ).addClass("mynavbar");
      } else {
         $( "#mainnavbar" ).removeClass("mynavbar");
      }
   })
});