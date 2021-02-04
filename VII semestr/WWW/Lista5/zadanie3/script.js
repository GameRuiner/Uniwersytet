$(function(){
   $.getJSON( "json.json", function( data ) {
            console.log(data);
            title = Mustache.render("{{{rss.channel.title.__cdata}}}",data);
            
            for(i = 0; i < 5; i++) {
               let titleLi = Mustache.render(`{{#rss.channel.item.${i}}} <li> {{{ title.__cdata }}}  </li> {{/rss.channel.item.${i}}}`, data);
               let linkLi = Mustache.render(`{{#rss.channel.item.${i}}} <li> <a href= ' {{ link }} '><span>Link</span></a> </li> {{/rss.channel.item.${i}}}`, data);
               let descriptionLi = Mustache.render(`{{#rss.channel.item.${i}}} <li> {{{ description.__cdata }}} </li> {{/rss.channel.item.${i}}}`, data);
               let ul = `<li> <ul> ${titleLi} ${linkLi} ${descriptionLi}  </ul> </li>`;
               $("[name='items']").append(ul);
            }
                                    
   });
   
});