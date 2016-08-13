/*$(function () {
	$('.numberOnly').keyup(function () { 
		this.value = this.value.replace(/[^0-9]/g,'');
    });
	
	$('.decimalOnly').keyup(function () { 
		this.value = this.value.replace(/[^0-9\.]/g,'');
    });
	
	$('.addrNumberOnly').keyup(function () { 
		this.value = this.value.replace(/[^0-9\-\/]/g,'');
    });
	
	$('.phoneNumberOnly').keyup(function () { 
		this.value = this.value.replace(/[^0-9\+]/g,'');
    });
	 
	 $(".numberWithComma").live("blur", function() {			 		
		 this.value = this.value.replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
	 }); 
 });*/

jQuery.extend({
    constant: {
        page: {
            HOME: 1
        }
    },
    cache: {},
    window: {
        go: function(link) {
            window.location = link;
        },

        popup: function(link) {
            window.open(link, 'POPUP', 'height=600, width=800, left=100, top=100, location=0, menubar=1, status=0, toolbar=0');
        }
    },
    kui: {
        dialog: {
            alert: function(message, title) {
                $('body').append('<div id="alertdialog" title="' + title + '">' + message + '</div>');
                $('#alertdialog').dialog({
                    modal: true,
                    resizable: false,
                    minWidth: 350,
                    minHeight: 150,
                    open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
                    buttons: [
                    {
                        text: 'Ok',
                        click: function() {
                        	$(this).dialog("destroy");
                        	$(this).remove();
                            //$(this).dialog("close");
                        }
                    }
                    ],
                    close: function() {
                        $(this).dialog("destroy");
                        $(this).remove();
                    }
                });
            },
            //confirm with out function hide & show select box
            confirmBox: function(message, title, oktext, okevent, canceltext, cancelevent) {
                $('body').append('<div id="confirmdialog" title="' + title + '">' + message + '</div>');            
                $('#confirmdialog').dialog({
                    modal: true,
                    resizable: false,
                    minWidth: 450,
                    minHeight: 150,
                    buttons: [
                    {
                        text: oktext,
                        click: function() {
                            $(this).dialog("close");
                            okevent();
                        }
                    },
                    {
                        text: canceltext == undefined ? 'Cancel' : canceltext,
                        click: cancelevent == undefined ? function() {
                            $(this).dialog("close");
                        } : function() {
                            $(this).dialog("close");
                            cancelevent();
                        }
                    }
                    ],
                    close: function() {                      	
                        $(this).dialog("destroy");
                        $(this).remove();
                    }           
                });
            },
            confirm: function(message, title, oktext, okevent, canceltext, cancelevent) {
                $('body').append('<div id="confirmdialog" title="' + title + '">' + message + '</div>');
                $('select').hide();
                $('#confirmdialog').dialog({
                    modal: true,
                    resizable: false,
                    minWidth: 350,
                    minHeight: 150,
//                    open: function(event, ui) {
//                        $(":button:contains('Cancel')").focus(); // Set focus to the [Ok] button
//                    },
                    buttons: [
                    {
                        text: oktext,
                        click: function() {
                            $(this).dialog("close");
                            okevent();
                        }
                    },
                    {
                        text: canceltext == undefined ? 'Cancel' : canceltext,
                        click: cancelevent == undefined ? function() {
                            $(this).dialog("close");
                        } : function() {
                            $(this).dialog("close");
                            cancelevent();
                        }
                    }
                    ],
                    close: function() {
                    	$('select').show();
                    	
                    	//$.unblockUI($('#collapse'));
                        $(this).dialog("destroy");
                        $(this).remove();
                    }
                });
            },

            prompt: function(message, title, oktext, okevent) {
                //$('body').append('<div id="promptdialog" title="' + title + '"><div class="label" style="padding-left: 0px;">' + message + '</div><div><input type="text" id="promptvalue" value="" style="width: 300px;" /></div></div>');
                $('body').append('<div id="promptdialog" title="' + title + '">'+
                		'<table style="width: 100%;">'+
                		'<tr><td class="label">' + message + '</td><td><input type="text" id="promptvalue" class="input-width" value=""/></td></tr></table>'+		
                		'</div>');
                $('#promptdialog').dialog({
                    modal: true,
                    resizable: false,
                    minWidth: 330,
                    minHeight: 150,
                    buttons: [
                    {
                        text: oktext,
                        click: function() {
                            var value = $('#promptvalue').val();
                            $(this).dialog("close");
                            okevent(value);
                        }
                    },
                    {
                        text: 'Cancel',
                        click: function() {
                            $(this).dialog("close");
                        }
                    }
                    ],
                    close: function() {
                        $(this).dialog("destroy");
                        $(this).remove();
                    }
                });
            },
            
            prompt2: function(message, message2, title, oktext, okevent) {
                $('body').append('<div id="promptdialog" title="' + title + '">'+
                		'<table style="width: 100%;">'+
                		'<tr><td class="label">' + message + '</td><td><input type="text" id="promptvalue1" class="input-width" value=""/></td></tr>'+
                		'<tr><td class="label">' + message2 + '</td><td><input type="text" id="promptvalue2" class="input-width" value=""/></td></tr></table>'+		
                		'</div>');
                $('#promptdialog').dialog({
                    modal: true,
                    resizable: false,
                    minWidth: 330,
                    minHeight: 150,
                    buttons: [
					{
					    text: oktext,
					    click: function() {
					        var value1 = $('#promptvalue1').val();
					        var value2 = $('#promptvalue2').val();
					        $(this).dialog("close");
					        okevent(value1, value2);
					    }
					},          
                    {
                        text: 'Cancel',
                        click: function() {
                            $(this).dialog("close");
                        }
                    }
                    ],
                    close: function() {
                        $(this).dialog("destroy");
                        $(this).remove();
                    }
                });
            },
            
            prompt3: function(message, title, fieldSize, oktext, okevent) {
                
                $('body').append('<div id="promptdialog" title="' + title + '">'+
                		'<table style="width: 100%;">'+
                		'<tr><td class="label">' + message + '</td><td><input type="text" id="promptvalue" maxlength="'+fieldSize+'" class="input-width" value=""/></td></tr></table>'+		
                		'</div>');
                $('#promptdialog').dialog({
                    modal: true,
                    resizable: false,
                    minWidth: 330,
                    minHeight: 150,
                    buttons: [
                    {
                        text: oktext,
                        click: function() {
                            var value = $('#promptvalue').val();
                            $(this).dialog("close");
                            okevent(value);
                        }
                    },
                    {
                        text: 'Cancel',
                        click: function() {
                            $(this).dialog("close");
                        }
                    }
                    ],
                    close: function() {
                        $(this).dialog("destroy");
                        $(this).remove();
                    }
                });
            }

        },
    
        widget: {

            notice: function(message) {
                if($('#notice').length == 0) {
                    $('body').append('<div id="notice" class="widget-notice widget-notice-notice">' + message + '</div>');
                    $('#notice').slideDown('fast');
                    $.cache.widgetnotice = setTimeout(function(){
                        $('#notice').slideUp('fast', function(){
                            $('#notice').remove();
                            $.cache.widgetnotice = undefined;
                        });
                    }, 10000);
                } else {
                    $('#notice').html(message);
                    clearTimeout($.cache.widgetnotice);
                    $.cache.widgetnotice = setTimeout(function(){
                        $('#notice').slideUp('fast', function(){
                            $('#notice').remove();
                            $.cache.widgetnotice = undefined;
                        });
                    }, 10000);
                }
            },

            warning: function(message) {
                if($('#warning').length == 0) {
                    $('body').append('<div id="warning" class="widget-notice widget-notice-warning">' + message + '</div>');
                    $('#warning').slideDown('fast');
                    $.cache.widgetwarning = setTimeout(function(){
                        $('#warning').slideUp('fast', function(){
                            $('#warning').remove();
                            $.cache.widgetwarning = undefined;
                        });
                    }, 10000);
                } else {
                    $('#warning').html(message);
                    clearTimeout($.cache.widgetwarning);
                    $.cache.widgetwarning = setTimeout(function(){
                        $('#warning').slideUp('fast', function(){
                            $('#warning').remove();
                            $.cache.widgetwarning = undefined;
                        });
                    }, 10000);
                }
            }

        }

    },

    postJSON: function(url, data, callback) {
        $.post(
            url,
            data,
            function(raw){
                var json = eval('('+raw+')');
                callback(json);
            },
            'text');
    }
});

jQuery.fn.extend({
    reset: function() {
        $(this).get(0).reset();
    },

    setOptions: function(data) {
        $.cache.optionvalue = '';
        $.each(data, function(k, v){
            $.cache.optionvalue += '<option value="' + v.value + '">' + v.key + '</option>';
        });
        $(this).html($.cache.optionvalue);
    },

    addOption: function(key, value) {
        $(this).append('<option value="' + value + '">' + key + '</option>');
    },

    addOptions: function(data) {
        $.cache.optionvalue = '';
        $.each(data, function(k, v){
            $.cache.optionvalue += '<option value="' + v.value + '">' + v.key + '</option>';
        });
        $(this).append($.cache.optionvalue);
    },

    removeSelectedOptions: function() {
        $('option:selected', this).remove();
    }

});

function ajaxPost(url, data, header, token, callback, unblock) {
	$.ajax({url: url, data: data, cache: false, type: "POST",
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
			$.blockUI({ message: 'Processing...',css: {
	            border: 'none', 
	            padding: '15px', 
	            backgroundColor: '#000', 
	            '-webkit-border-radius': '10px', 
	            '-moz-border-radius': '10px', 
	            opacity: .5, 
	            color: '#fff' 
	        }});
		}})
		.done(callback)
		.fail(function(jqXHR, textStatus, errorThrown) {
			console.log('jqXHR: ' + jqXHR);
			console.log('textStatus: ' + textStatus);
			console.log('errorThrown: ' + errorThrown);
			alert(errorThrown);
		})
		.always(function() {
			$.unblockUI();
		});
}

function ajaxGet(url, data, header, token, callback, unblock) {
	$.ajax({url: url, data: data, cache: false, type: "GET",
		beforeSend: function() {
			$.blockUI({ message: 'Processing...',css: {
	            border: 'none', 
	            padding: '15px', 
	            backgroundColor: '#000', 
	            '-webkit-border-radius': '10px', 
	            '-moz-border-radius': '10px', 
	            opacity: .5, 
	            color: '#fff' 
	        }});
		}})
		.done(callback)
		.fail(function(jqXHR, textStatus, errorThrown) {
			console.log('jqXHR: ' + jqXHR);
			console.log('textStatus: ' + textStatus);
			console.log('errorThrown: ' + errorThrown);
			alert(errorThrown);
		})
		.always(function() {
			$.unblockUI();
		});
}

function checknum() {
	var key;
	if (window.event) {
		key = window.event.keyCode;
	} else if (e) {
	    key = e.which;
	} else {
	    return true;
	}
		
	if (key==null || key == 8 || key == 37 || key == 39 || key == 46 || (key >=48 && key<=57)) {
	   return true;
	}
	else {
		return false;
	}
}
	
function checknum2() {
	var key;
	if (window.event) {
	   key = window.event.keyCode;
	} else if (e) {
	    key = e.which;
	} else {
	    return true;
	}
		
	if (key==null || (key >=48 && key<=57)) {
	    return true;
	}
	else {
		return false;
	}
}

function isInteger(n) {
    var result = (/^-?\d+$/.test(n+''));
	if(result) {
		if(n.indexOf('-') > -1) {
			result = false;
		}
	}
	
	return result ;
}

function validateDecimalPrecision(obj, prefix, suffix) {
	var val = obj.val().replace(/,/g, "");
	if (isNaN(val)) {
		obj.val(val.substr(0, val.length-1));
	}

	if (val.indexOf(".") > -1) {
		var tmp = val.split(".");
		if (prefix > 0) {
			if (tmp[0].length > prefix) {
				//tmp[0] = tmp[0].substr(0, 2);
				obj.val("");
				return;
			}
		}
		if (suffix > 0) {
			if (tmp[1].length > suffix) {
				//tmp[1] = tmp[1].substr(0,2);
				obj.val("");
				return;
			}
		}
		//document.getElementById(id).value = tmp[0]+"."+tmp[1];
	}
	else {
		if (prefix > 0) {
			if (val.length > prefix) {
				//document.getElementById(id).value = val.substr(0, 2);
				obj.val("");
				return;
			}
		}
	}
}
