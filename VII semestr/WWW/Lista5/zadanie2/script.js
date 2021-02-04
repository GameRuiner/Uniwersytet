$(function(){
   $.get( "https://tvn24.pl/najnowsze.xml", function( data ) {
             
            console.log(data);
            //let xmlDoc = $.parseXML( data );
            $xml = $( data );
            $title = $xml.find( "title" );
            $("#htitle").text($title.first().text());
            $xml.find("item").slice(0,5).each(function( index ) {
               
               let titleLi = `<li><h3>${  $( this ).find("title").text() }</h3></li>`;
               let linkLi = `<li> <a href= "${ $( this ).find("link").text() }"><span>Link</span></a> </li>`;
               let descriptionLi =  `<li>${  $( this ).find("description").text() }</li>`;
               let ul = `<li> <ul> ${titleLi} ${linkLi} ${descriptionLi}  </ul> </li>`;
               $("[name='items']").append(ul);
            });
            
            $("#searchbutton").on('click', function () {
               let phrase = $('#searchphrase').val();
               let item =  $( $xml.xpath(`//item[contains(., "${ phrase }")]`)[0] );
               if (item.length != 0) {
               $("#searchresult").html(`
                   <li> ${item.find("title").text()} </li>
                   <li> <a href= "${ item.find("link").text() }"><span>Link</span></a> </li>
                   <li> ${ item.find("description").text() }" </li>
                  `);
               } else {
                $("#searchresult").html("");
               }
               
            });
            
            
  // }});
   });
   
});