jQuery.extend({
    createExportIframe: function(id, uri)
	{
			//create frame
            var frameId = 'jExportFrame' + id;
            var iframeHtml = '<iframe id="' + frameId + '" name="' + frameId + '" style="position:absolute; top:-9999px; left:-9999px"';
			if(window.ActiveXObject)
			{
                if(typeof uri== 'boolean'){
					iframeHtml += ' src="' + 'javascript:false' + '"';

                }
                else if(typeof uri== 'string'){
					iframeHtml += ' src="' + uri + '"';

                }	
			}
			iframeHtml += ' />';
			jQuery(iframeHtml).appendTo(document.body);

            return jQuery('#' + frameId).get(0);			
    },
    createExportForm: function(id, fileElementId, data)
	{
		//create form	
		var formId = 'jExportForm' + id;
		var fileId = 'jExportFile' + id;
		var form = jQuery('<form  action="#" method="POST" name="' + formId + '" id="' + formId + '"></form>');	
		if(data)
		{
			for(var i in data)
			{
				jQuery('<input type=\'hidden\' name=\'' + i + '\' value=\'' + data[i] + '\' />').appendTo(form);
			}			
		}		
		
		jQuery(form).css('position', 'absolute');
		jQuery(form).css('top', '-1200px');
		jQuery(form).css('left', '-1200px');
		jQuery(form).appendTo('body');	
		return form;
    },

    ajaxExcelExport: function(s) {
        s = jQuery.extend({}, jQuery.ajaxSettings, s);
        var thead = jQuery("thead",jQuery("#"+s.tableId));
        var titles=[];
        var datas = [];
        var trs =jQuery("tr",jQuery(thead));
        for(i=0;i<trs.length;i++){
        	var tr = $(trs).get(i);
    		var head=[] ;
        	for(j=0;j<$("th,td",tr).length;j++){
        		var th = $("th,td",tr).get(j);
        		var v = $(th).text() ;
        		var rowspan = $(th).attr("rowspan");
        		var colspan = $(th).attr("colspan");
        		var dataType = $(th).attr("dataType");
        		if(!rowspan){
        			rowspan=1 ;
        		}
        		if(!colspan){
        			colspan=1 ;
        		}
        		head.push({"value":v,"dataType":dataType,"rowspan":rowspan,"colspan":colspan});
        	}
    		titles.push(head);
        }
        var tbody = jQuery("tbody",jQuery("#"+s.tableId));
        trs =jQuery("tr",jQuery(tbody));
        for(i=0;i<trs.length;i++){
        	var tr = $(trs).get(i);
    		var row=[] ;
        	for(j=0;j<$("td,th",tr).length;j++){
        		var td = $("td,th",tr).get(j);
        		var v = $(td).text() ;
        		var dataType = $(td).attr("dataType");
        		var rowspan = $(td).attr("rowspan");
        		var colspan = $(td).attr("colspan");
        		if(!rowspan){
        			rowspan=1 ;
        		}
        		if(!colspan){
        			colspan=1 ;
        		}
        		if(!dataType){
        			dataType="string" ;
        		}
        		row.push({"value":v,"dataType":dataType,"rowspan":rowspan,"colspan":colspan});
        	}
    		datas.push(row) ;
        }
        
    	s.data.tabletitles= JSON.stringify(titles);
        s.data.tableRows=JSON.stringify(datas);
//        console.log(s);
        var id = new Date().getTime()       ;  
		var form = jQuery.createExportForm(id, s.fileElementId, (typeof(s.data)=='undefined'?false:s.data));
		var io = jQuery.createExportIframe(id, s.secureuri);
		
		var frameId = 'jExportFrame' + id;
		var formId = 'jExportForm' + id;		
        // Watch for a new set of requests
        if ( s.global && ! jQuery.active++ )
		{
			jQuery.event.trigger( "ajaxStart" );
		}            
        var requestDone = false;
        // Create the request object
        var xml = {}  ; 
        if ( s.global )
            jQuery.event.trigger("ajaxSend", [xml, s]);
        // Wait for a response to come back
        var uploadCallback = function(isTimeout)
		{			
			var io = document.getElementById(frameId);
            try 
			{				
				if(io.contentWindow)
				{
					 xml.responseText = io.contentWindow.document.body?io.contentWindow.document.body.innerHTML:null;
                	 xml.responseXML = io.contentWindow.document.XMLDocument?io.contentWindow.document.XMLDocument:io.contentWindow.document;
					 
				}else if(io.contentDocument)
				{
					 xml.responseText = io.contentDocument.document.body?io.contentDocument.document.body.innerHTML:null;
                	xml.responseXML = io.contentDocument.document.XMLDocument?io.contentDocument.document.XMLDocument:io.contentDocument.document;
				}						
            }catch(e)
			{
				jQuery.handleError(s, xml, null, e);
			}
            if ( xml || isTimeout == "timeout") 
			{				
                requestDone = true;
                var status;
                try {
                    status = isTimeout != "timeout" ? "success" : "error";
                    // Make sure that the request was successful or notmodified
                    if ( status != "error" )
					{
                        // process the data (runs the xml through httpData regardless of callback)
                        var data = jQuery.uploadHttpData( xml, s.dataType );    
                        // If a local callback was specified, fire it and pass it the data
                        if ( s.success )
                            s.success( data, status );
    
                        // Fire the global callback
                        if( s.global )
                            jQuery.event.trigger( "ajaxSuccess", [xml, s] );
                    } else
                        jQuery.handleError(s, xml, status);
                } catch(e) 
				{
                    status = "error";
                    jQuery.handleError(s, xml, status, e);
                }

                // The request was completed
                if( s.global )
                    jQuery.event.trigger( "ajaxComplete", [xml, s] );

                // Handle the global AJAX counter
                if ( s.global && ! --jQuery.active )
                    jQuery.event.trigger( "ajaxStop" );

                // Process result
                if ( s.complete )
                    s.complete(xml, status);

                jQuery(io).unbind() ;

                setTimeout(function()
									{	try 
										{
											jQuery(io).remove();
											jQuery(form).remove();	
											
										} catch(e) 
										{
											jQuery.handleError(s, xml, null, e);
										}									

									}, 100)

                xml = null

            }
        }
        if ( s.timeout > 0 ) 
		{
            setTimeout(function(){
                if( !requestDone ) uploadCallback( "timeout" );
            }, s.timeout);
        }
        try 
		{

			var form = jQuery('#' + formId);
			jQuery(form).attr('action', s.url);
			jQuery(form).attr('method', 'POST');
			jQuery(form).attr('target', frameId);	
            jQuery(form).submit();

        } catch(e) 
		{			
            jQuery.handleError(s, xml, null, e);
        }
		
		jQuery('#' + frameId).load(uploadCallback	);
        return {abort: function () {}};	

    },

    exportHttpData: function( r, type ) {
        var data = !type;
        data = type == "xml" || data ? r.responseXML : r.responseText;
        // If the type is "script", eval it in global context
        if ( type == "script" )
            jQuery.globalEval( data );
        // Get the JavaScript object, if JSON is used.
        if ( type == "json" )
            eval( "data = " + data );
        // evaluate scripts within html
        if ( type == "html" )
            jQuery("<div>").html(data).evalScripts();

        return data;
    },handleError: function( s, xhr, status, e ) {
        // If a local callback was specified, fire it
        if ( s.error )
            s.error( xhr, status, e );
        // If we have some XML response text (e.g. from an AJAX call) then log it in the console
        else if(xhr.responseText)
            console.log(xhr.responseText);
    }
})

