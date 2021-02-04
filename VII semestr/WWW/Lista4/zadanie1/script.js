$(function(){
   
   let searchInput = $("[name='searchphrace']");
   searchInput.on('keyup', changeInput);
      
   function changeInput() {
      let searchText = $( this ).val();
      if (searchText.length > 2) {
         $("[name='items']").children().each(function(index) {
            replaceMatch($(this));
            if ($( this ).text().includes(searchText)) {
               
               //let r = new RegExp(searchText.replace(/\./g,'\\.'),'g');
               //let r = new RegExp(searchText,'g');
           
               $( this ).html($( this ).html().replace(
                     searchText,
                     '<span class="search-match">'+searchText+'</span>'
               ));
               
               //$( this ).addClass("search-match");
               $( this ).removeClass("no-search-match");
               
            } else {
               $( this ).addClass("no-search-match");
               
               replaceMatch($(this));
               //$( this ).removeClass("search-match");
            }
         });
      } else {
         $("[name='items']").children().each(function() {
            replaceMatch($(this))
            $( this ).removeClass(["search-match","no-search-match"]);
         });
      }
   }
   
   function replaceMatch(elem){
      let r1 = new RegExp('<span class="search-match">','g');
      let r2 = new RegExp('</span>','g');
      elem.html(elem.html().replace(r1,""));
      elem.html(elem.html().replace(r2,""));
   }
   
});