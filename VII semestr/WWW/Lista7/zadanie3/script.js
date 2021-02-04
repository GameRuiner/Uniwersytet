$( function() {
   $(".btnDelete").on('click', function () {
      console.log('hi');
    $(this).closest('tr').remove();
   });
});