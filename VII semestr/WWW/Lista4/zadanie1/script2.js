$(function(){
   
   let searchInput = $("[name='searchphrace']");
   searchInput.on('keyup', changeInput);
      
   function changeInput() {
      let searchText = $( this ).val();
      if (searchText.length > 2) {
         $("[name='items']").children().each(function(index) {
            if ($( this ).text().includes(searchText)) {
               
               let str = $( this ).text();
               
               let start = 0;
               let addOffset =  '<span class="search-match"></span>'.length;
               while ((index = str.indexOf(searchText, start)) != -1) {
                  $( this ).text(str.substring(0, index) +
                                 '<span class="search-match">' + 
                                 str.substring(index, index+searchText.length) + 
                                 '</span>' + 
                                 str.substring(index+searchText.length));
                  start=index+addOffset;
                  str = $( this ).text();
               }
               $( this ).html($( this ).text());
               
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
            $( this ).removeClass(["search-match","no-search-match"]);
            replaceMatch($(this));
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