var EditableTable = function () {

    return {

        //main function to initiate the module
        init: function () {
            function restoreRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);

                for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                    oTable.fnUpdate(aData[i], nRow, i, false);
                }

                oTable.fnDraw();
            }

            function editRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                jqTds[0].innerHTML = '<input type="text" class="form-control small" value="' + aData[0] + '">';
                jqTds[1].innerHTML = '<input type="text" class="form-control small" value="' + aData[1] + '">';
                jqTds[2].innerHTML = '<a class="edit" href="">保存</a>';
                jqTds[3].innerHTML = '<a class="cancel" href="">取消</a>';
            }
            
            function toEditRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                jqTds[0].innerHTML = '<input type="text" class="form-control small" value="' + aData[0] + '">';
                jqTds[1].innerHTML = '<input type="text" class="form-control small" value="' + aData[1] + '">';
                jqTds[2].innerHTML = '<a class="edit" href="">保存修改</a>';
                jqTds[3].innerHTML = '<a class="cancel" href="">取消</a>';
            }

            function saveRow(oTable, nRow) {
                var jqInputs = $('input', nRow);
                var result = addCate(jqInputs);
                if(result == "success"){
                oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
                oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
                oTable.fnUpdate('<a class="edit" href="">编辑</a>', nRow, 2, false);
                oTable.fnUpdate('<a class="delete" href="">删除</a>', nRow, 3, false);
                oTable.fnDraw();
            	}else{
            		alert("系统错误，请刷新重试");
            	}
            }
            
            function saveEditRow(oTable, nRow, cid) {
                var jqInputs = $('input', nRow);
                var result = editCate(jqInputs,cid);
                if(result == "success"){
                oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
                oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
                oTable.fnUpdate('<a class="edit" href="">编辑</a>', nRow, 2, false);
                oTable.fnUpdate('<a class="delete" href="">删除</a>', nRow, 3, false);
                oTable.fnDraw();
            	}else{
            		alert("系统错误，请刷新重试");
            	}
            }

            function cancelEditRow(oTable, nRow) {
                var jqInputs = $('input', nRow);
                oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
                oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
                oTable.fnUpdate('<a class="edit" href="">编辑</a>', nRow, 2, false);
                oTable.fnDraw();
            }

            var oTable = $('#editable-sample').dataTable({
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "所有"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 5,
                "sDom": "<'row'<'col-lg-6'l><'col-lg-6'f>r>t<'row'<'col-lg-6'i><'col-lg-6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage": {
                    "sLengthMenu": "_MENU_ 条记录每页",
                    "oPaginate": {
                        "sPrevious": "上一页",
                        "sNext": "下一页"
                    }
                },
                "aoColumnDefs": [{
                        'bSortable': false,
                        'aTargets': [0]
                    }
                ]
            });

            jQuery('#editable-sample_wrapper .dataTables_filter input').addClass("form-control medium"); // modify table search input
            jQuery('#editable-sample_wrapper .dataTables_length select').addClass("form-control xsmall"); // modify table per page dropdown

            var nEditing = null;

            $('#editable-sample_new').click(function (e) {
                e.preventDefault();
                var aiNew = oTable.fnAddData(['', '',
                        '<a class="edit" href="">编辑</a>', '<a class="cancel" data-mode="new" href="">取消</a>'
                ]);
                var nRow = oTable.fnGetNodes(aiNew[0]);
                editRow(oTable, nRow);
                nEditing = nRow;
            });

            $('#editable-sample a.delete').live('click', function (e) {
                e.preventDefault();

                if (confirm("将会删除下属的二级分类，确定 ?") == false) {
                    return;
                }
                var cid = $(this).parent().parent().attr("cid");
                var result = delCate(cid);
                if(result == "success"){
                	var nRow = $(this).parents('tr')[0];
                	oTable.fnDeleteRow(nRow);
                }
            });

            $('#editable-sample a.cancel').live('click', function (e) {
                e.preventDefault();
                if ($(this).attr("data-mode") == "new") {
                    var nRow = $(this).parents('tr')[0];
                    oTable.fnDeleteRow(nRow);
                } else {
                	restoreRow(oTable, nEditing);
                    nEditing = null;  
                }
            });

            $('#editable-sample a.edit').live('click', function (e) {
                e.preventDefault();

                /* Get the row as a parent of the link that was clicked on */
                var nRow = $(this).parents('tr')[0];

                if (nEditing !== null && nEditing != nRow) {
                    /* Currently editing - but not this row - restore the old before continuing to edit mode */
                    restoreRow(oTable, nEditing);
                    editRow(oTable, nRow);
                    nEditing = nRow;
                } else if (nEditing == nRow && this.innerHTML == "保存") {
                    /* Editing this row and want to save it */
                    saveRow(oTable, nEditing);
                    nEditing = null;                    
                } else if (nEditing == nRow && this.innerHTML == "保存修改") {
                    /* Editing this row and want to save it */
                	var cid = $(this).parent().parent().attr("cid");
                    saveEditRow(oTable, nEditing, cid);
                    nEditing = null;                    
                } else {
                    /* No edit in progress - let's start one */
                	toEditRow(oTable, nRow);
                    nEditing = nRow;
                }
            });
        }

    };

    
}();

function addCate(jqInputs){
	var result = null;
	$.ajax({
		url:path+"/category/addCategory.do",
		type:"post",
		dataType:"text",
		async:false,
		data:{
			name:jqInputs[0].value,
			icon:jqInputs[1].value
		},
		success:function(responseText){			
			result = responseText;
		},
		error:function(){
			alert("系统错误");
		}
	});

	return result;
}

function delCate(cid){
	var result = null;
	$.ajax({
		url:path+"/category/delCategory.do",
		type:"post",
		dataType:"text",
		async:false,
		data:{
			id:cid
		},
		success:function(responseText){			
			result = responseText;
		},
		error:function(){
			alert("系统错误");
		}
	});

	return result;
}

function editCate(jqInputs,cid){
	var result = null;
	$.ajax({
		url:path+"/category/modifyCategory.do",
		type:"post",
		dataType:"text",
		async:false,
		data:{
			id:cid,
			name:jqInputs[0].value,
			icon:jqInputs[1].value
		},
		success:function(responseText){			
			result = responseText;
		},
		error:function(){
			alert("系统错误");
		}
	});

	return result;
}