/**
 * Created by 何鹏帅 on 2016/12/29.
 */
var schoolYears;
$(function () {
    $('#home-content').ready(function () {
        getSchoolYears();
    });

    $('#btn-change').click(function () {
        window.location.href = "changepwd.html";
    });

    // 修改事件
    $(document).on('click', '.modify', function () {
        var param = "paperID=" + $(this).attr('paperID');
        $.ajax({
            type: "POST",
            url:  "allow_modify",
            dataType: "JSON",
            data: param,
            success:function(data){
                if(data['code'] == 0) {
                    bootbox.alert(data['message']);
                } else {
                    window.location.href = "modify.html?" + param;
                }
            }
        });
    });

    // 查询按钮事件
    $('#btn-search').click(function () {
        getPaperListByTerm();
    });

    // 删除事件
    $(document).on('click', '.delete', function () {
        var param = "paperID=" + $(this).attr('paperID');
        $.ajax({
            type: "POST",
            url:  "allow_modify",
            dataType: "JSON",
            data: param,
            success:function(data){
                if(data['code'] == 0) {
                    bootbox.alert("试卷已经送印，不能删除");
                } else {
                    $.ajax({
                        type: "POST",
                        url:  "delete_paper_by_id",
                        dataType: "JSON",
                        data: param,
                        success:function(data){
                            if(data['code'] == 0) {
                                bootbox.alert("试卷删除失败");
                            } else {
                                bootbox.alert("试卷删除成功", function () {
                                    window.location.href = "home.html";
                                });

                            }
                        },
                        error:function () {
                            bootbox.alert("删除失败");
                        }
                    });
                }
            },
            error:function () {
                bootbox.alert("删除失败");
            }
        });
    });
});



// 获取某个学期的试卷列表
function getPaperListByTerm() {
    var schoolYear = $('#school-year option:selected').val();
    var schoolYearID;
    for(var i = 0; i < schoolYears.length; i++) {
        if(schoolYear == schoolYears[i]['schoolyearname']) {
            schoolYearID = schoolYears[i]['id'];
            break;
        }
    }

    var term = $('#term option:selected').val();
    var param = "username=" + sessionStorage.getItem("username") + "&schoolyear=" + schoolYearID + "&term="
                + term;
    $.ajax({
        type: "POST",
        url:  "get_paper_list_by_term",
        dataType: "JSON",
        data: param,
        success:function(data){
            $('#tbcontent').children("tr").remove();
            for(var i = 0; i < data.length; i++) {
                var unixTimestamp = new Date(data[i]['testtime'] * 1000);
                var commonTime = unixTimestamp.toLocaleString();
                var testtime = commonTime.split(' ')[0];
                unixTimestamp = new Date(data[i]['uploadtime'] * 1000);
                commonTime = unixTimestamp.toLocaleString();
                var uploadtime = commonTime;
                $('#tbcontent').append("<tr><td>" + data[i]['course'] + "</td><td>" + data[i]['schoolyear']
                    + "</td><td>" + data[i]['term'] + "</td><td>" + data[i]['institute'] + "</td><td>" + data[i]['profession']
                    + "</td><td>" + data[i]['papernumber'] + "</td><td>" + data[i]['answersheetnumber'] + "</td><td>" + data[i]['scratchpapernumber']
                    + "</td><td>" + data[i]['papertype'] + "</td><td>" + testtime + "</td><td>" + uploadtime
                    + "</td><td>" + data[i]['status'] + "</td><td>" + "<a href='#' class='modify' paperID=" + data[i]['id'] + ">修改 </a>"
                    + "<a href='#' class='delete' paperID=" + data[i]['id'] + ">删除</a>"+ "</td></tr>");
            }
            var i = 0;
            $('table tr').each(function () {
                if(i % 2 == 1) {
                    $(this).addClass("success");
                }
                i++;
            })
        },
        error: function () {
            bootbox.alert("试卷列表加载失败");
        }
    });
}

/**
 * 从后端获取学年信息
 */
function getSchoolYears() {
    $.ajax({
        type: "POST",
        url:  "get_school_years",
        dataType: "JSON",
        data: "",
        success:function(data){
            schoolYears = data;
            for(var i = 0; i <schoolYears.length;i++) {
                $('#school-year').append("<option>" + schoolYears[i]['schoolyearname'] + "</option>");
            }

            var date = new Date();
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var schoolyear1;
            if(month < 9) {
                schoolyear1 = "" + (year - 1) + "-" + year;
            } else {
                schoolyear1 = "" + year + "-" + (year + 1);
            }
            if(month > 2 && month < 9) {
                $('#term').val("第2学期");
            } else {
                $('#term').val("第1学期")
            }

            $('#school-year').val(schoolyear1);
            getPaperListByTerm();
        },
        error:function (data) {
            var msg = data['responseJSON'];
            bootbox.alert(msg['message']);
        }
    });
}