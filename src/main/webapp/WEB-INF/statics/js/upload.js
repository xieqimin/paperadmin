/**
 * Created by 何鹏帅 on 2016/11/27.
 */
var schoolYears = new Array();
var institutes = new Array();
$(function () {
    $('#content1').ready(function () {
        checkCurrentSchoolYear();
        getInstitutes();
        $('#username').val(sessionStorage.getItem("username"));
        $('.sl-term').change();
        $('.sl-paper-type').change();
    });

    $('.sl-institute').change(function () {
        $('#pro').val("");
        getProfessions();
        var instituteName = $('.sl-institute option:selected').val();
        for(var i = 0; i<institutes.length; i++) {
            if(instituteName == institutes[i]['institutename']) {
                $('#institute-id').val(institutes[i]['id']);
            }
        }
    });

    $('.sl-term').change(function () {
        var term = $('.sl-term option:selected').val();
        $('#term').val(term);
    });

    $('.sl-school-year').change(function () {
        var schoolYearName = $('.sl-school-year option:selected').val();
        for(var i = 0; i <schoolYears.length; i++) {
            if(schoolYearName == schoolYears[i]['schoolyearname']) {
                $('#school-year-id').val(schoolYears[i]['id']);
            }
        }
    });

    $('.sl-profession').change(function () {
        var professionName = $('.sl-profession option:selected').val();
        $('#profession-id').val(professionName);
    });

    $('.sl-paper-type').change(function () {
        var type = $('.sl-paper-type option:selected').val();
        $('#paper-type').val(type);
    });

    $('#test-time').change(function () {
        var testtime = $('#test-time').val();
        var testtimestr = Math.round(new Date(testtime).getTime()/1000);
        $("#testtime").val(testtimestr);
    })

    var options = {
        dataType:'json',
        beforeSubmit: checkInfo,
        success:function (data) {
            bootbox.alert(data['message'], function () {
                window.location.href = "home.html";
            });
            $('#upfile').val("");
        },
        error: function (data) {
            var msg = data['responseJSON'];
            bootbox.alert(msg['message']);
        }
    };
    $('#paper-upload').ajaxForm(options);

    // 提交前验证
    function checkInfo() {
        if($('#upfile').val() == "") {
            bootbox.alert("请选择试卷");
            return false;
        }

        // 验证课程名
        if($('#course').val() == "") {
            bootbox.alert("请输入课程名");
            return false;
        }

        // 验证专业是否为空
        if($('#pro').val().trim() == "") {
            bootbox.alert("请输入专业");
            return false;
        }

        // 验证试卷份数
        if($('#papernumber').val() == "") {
            bootbox.alert("请输入试卷份数");
            return false;
        } else {
            var papernumber = $('#papernumber').val().trim();
            if(!/^\d+$/.test(papernumber)) {
                bootbox.alert("试卷份数只能为整数");
                return false;
            }
        }

        // 验证答题纸份数
        if($('#answersheetnumber').val() == "") {
            bootbox.alert("请输入答题纸份数");
            return false;
        } else {
            var answersheetnumber = $('#answersheetnumber').val().trim();
            if(!/^\d+$/.test(answersheetnumber)) {
                bootbox.alert("答题纸份数只能为整数");
                return false;
            }
        }

        // 验证草稿纸份数
        if($('#scratchpapernumber').val() == "") {
            bootbox.alert("请输入草稿纸份数");
            return false;
        } else {
            var scratchpapernumber = $('#scratchpapernumber').val().trim();
            if(!/^\d+$/.test(scratchpapernumber)) {
                bootbox.alert("草稿纸份数只能为整数");
                return false;
            }
        }

        if($('#test-time').val() == "") {
            bootbox.alert("请输入考试时间");
            return false;
        }
        // 验证考试时间格式
        if(!IsDate($('#test-time').val().trim())) {
            return false;
        }

        return true;
    }

    // 上传按钮事件
    $('#btn-upload').click(function () {
        // 上传试卷
        $('#paper-upload').submit();
    })

    $('#btn-back').click(function () {
        window.location.href = "home.html";
    })
});

function checkCurrentSchoolYear() {
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var schoolyear1;
    if(month < 9) {
        schoolyear1 = "" + (year - 1) + "-" + year;
    } else {
        schoolyear1 = "" + year + "-" + (year + 1);
    }
    $.ajax({
        type: "POST",
        url:  "check_current_school_year",
        dataType: "JSON",
        data: "schoolyear=" + schoolyear1,
        success:function(data){
            getSchoolYears();
        },
        error:function (data) {
            getSchoolYears();
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
                $('.sl-school-year').append("<option>" + schoolYears[i]['schoolyearname'] + "</option>");
            }
            $('#school-year-id').val(schoolYears[0]['id']);
        },
        error:function (data) {
            var msg = data['responseJSON'];
            bootbox.alert(msg['message']);
        }
    });
}

/**
 * 获取学院信息
 */
function getInstitutes() {
    $.ajax({
        type: "POST",
        url:  "get_institutes",
        dataType: "JSON",
        data: "",
        success:function(data){
            institutes = data;
            for(var i = 0; i <institutes.length;i++) {
                $('.sl-institute').append("<option>" + institutes[i]['institutename'] + "</option>");
            }
            $('.sl-institute').change();
        },
        error:function (data) {
            var msg = data['responseJSON'];
            bootbox.alert(msg['message']);
        }
    });
}

/**
 * 获取专业信息
 */
function getProfessions() {
    var instituteName = $('.sl-institute option:selected').val();
    var instituteID;
    for(var i = 0; i<institutes.length; i++) {
        if(instituteName == institutes[i]['institutename']) {
            instituteID = institutes[i]['id'];
            break;
        }
    }
    $.ajax({
        type: "POST",
        url:  "get_professions",
        dataType: "JSON",
        data: "instituteID=" + instituteID,
        success:function(data){
            $('.sl-profession').children('option').remove();
            for(var i = 0; i <data.length;i++) {
                $('.sl-profession').append("<option>" + data[i] + "</option>");
            }
        },
        error:function (data) {
            var msg = data['responseJSON'];
            bootbox.alert(msg['message']);
        }
    });
}

// 检查日期格式是否为yyyy-mm-dd
function   IsDate(mystring)   {
    var   reg   =   /^(\d{4})-(\d{2})-(\d{2})$/;
    var   str   =   mystring;
    if   (!reg.test(str) || str==""){
        bootbox.alert("请保证考试时间中输入的日期格式为yyyy-mm-dd或正确的日期!");
        return   false;
    }
    return   true;
}