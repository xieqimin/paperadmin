/**
 * Created by 何鹏帅 on 2016/12/29.
 */
var schoolYears = new Array();
var institutes = new Array();
var paperInfo = {};
var tag = 0;
var pro;
var professiontag = 0;
$(function () {
    $('#modify-content').ready(function () {
        getSchoolYears();
    });

    $('.sl-institute').change(function () {
        var instituteName = $('.sl-institute option:selected').val();
        getProfessions();
        for(var i = 0; i<institutes.length; i++) {
            if(instituteName == institutes[i]['institutename']) {
              paperInfo['instituteid'] =institutes[i]['id'];
            }
        }
    });

    $('.sl-term').change(function () {
        paperInfo['term'] = $('.sl-term option:selected').val();
    });

    $('.sl-school-year').change(function () {
        var schoolYearName = $('.sl-school-year option:selected').val();
        for(var i = 0; i <schoolYears.length; i++) {
            if(schoolYearName == schoolYears[i]['schoolyearname']) {
                paperInfo['schoolyearid'] =  schoolYears[i]['id'];
            }
        }
    });

    $('.sl-profession').change(function () {
        paperInfo['profession'] = $('.sl-profession option:selected').val();

    });

    $('.sl-paper-type').change(function () {
        paperInfo['papertype'] = $('.sl-paper-type option:selected').val();
    });

    $('#testtime').change(function () {
        var testtime = $('#testtime').val();
        paperInfo['testtime'] = Math.round(new Date(testtime).getTime()/1000);
    });

    // 提交试卷修改信息
    $('#btn-certain').click(function () {
        if($('#course').val().trim() == "") {
            bootbox.alert('请输入课程名');
            return;
        }

        if($('#pro').val().trim() == "") {
            bootbox.alert("请输入专业");
            return;
        }
        // 验证试卷份数格式
        if($('#papernumber').val().trim() == "") {
            bootbox.alert('请输入试卷份数');
            return;
        } else {
            var papernumber = $('#papernumber').val().trim();
            if(!/^\d+$/.test(papernumber)) {
                bootbox.alert("试卷份数只能为整数");
                return;
            }
        }

        // 验证答题纸份数格式
        if($('#answersheetnumber').val().trim() == "") {
            bootbox.alert('请输入答题纸份数');
            return;
        } else {
            var answersheetnumber = $('#answersheetnumber').val().trim();
            if(!/^\d+$/.test(answersheetnumber)) {
                bootbox.alert("答题纸份数只能为整数");
                return;
            }
        }

        // 验证草稿纸份数格式
        if($('#scratchpapernumber').val().trim() == "") {
            bootbox.alert('请输入草稿纸份数');
            return;
        } else {
            var scratchpapernumber = $('#scratchpapernumber').val().trim();
            if(!/^\d+$/.test(scratchpapernumber)) {
                bootbox.alert("草稿纸份数只能为整数");
                return;
            }
        }

        // 验证考试时间格式
        if(!IsDate($('#testtime').val().trim())) {
            return;
        }
        paperInfo['course'] = $('#course').val().trim();
        paperInfo['papernumber'] = $('#papernumber').val().trim();
        paperInfo['answersheetnumber'] = $('#answersheetnumber').val().trim();
        paperInfo['scratchpapernumber'] = $('#scratchpapernumber').val().trim();

        $.ajax({
            type: "POST",
            url:  "modify_paper_info",
            contentType: "application/JSON",
            dataType: "JSON",
            data: JSON.stringify(paperInfo),
            success:function(data){
                if(data['code'] == 0) {
                    bootbox.alert(data['message']);
                } else {
                    bootbox.alert(data['message'], function () {
                        window.location.href = "home.html";
                    });
                }
            },
            error:function (data) {
                var msg = data['responseJSON'];
                bootbox.alert(msg['message']);
            }
        });
    });

    $('#btn-cancel').click(function () {
        window.location.href = "home.html";
    });
});
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
            getInstitutes();
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
            paperInfo['profession'] = data[0];
            tag++;
            if(tag == 1) {
                getPaperInfoByID();
            }
            if(professiontag == 1) {
                $('#pro').val(pro);
                professiontag = 0;
            }
        },
        error:function (data) {
            var msg = data['responseJSON'];
            bootbox.alert(msg['message']);
        }
    });
}

// 根据试卷ID获取试卷信息
function getPaperInfoByID() {
    var paperID = getUrlParam("paperID");
    $.ajax({
        type: "POST",
        url:  "get_paper_info_by_id",
        dataType: "JSON",
        data: "paperID=" + paperID,
        success:function(data){
            paperInfo = data;
            $('#course').val(data['course']);
            $('#papernumber').val(data['papernumber']);
            $('#answersheetnumber').val(data['answersheetnumber']);
            $('#scratchpapernumber').val(data['scratchpapernumber']);
            var unixTimestamp = new Date(data['testtime'] * 1000);
            var testtime = unixTimestamp.toLocaleString().split(' ')[0];
            var dates = testtime.split("/");
            if(dates[1] < 10) {
                dates[0] = dates[0] + "-0" + dates[1];
            } else {
                dates[0] = dates[0] + "-" + dates[1];
            }
            if(dates[2] < 10) {
                dates[0] = dates[0] + "-0" + dates[2];
            } else {
                dates[0] = dates[0] + "-" + dates[2];
            }
            document.getElementById("testtime").value = dates[0].trim();
            $('.sl-term').val(data['term']);
            $('.sl-paper-type').val(data['papertype']);

            for(var i = 0; i < schoolYears.length; i++) {
                if(data['schoolyearid'] == schoolYears[i]['id']) {
                    $('.sl-school-year').val(schoolYears[i]['schoolyearname']);
                }
            }

            for(var i = 0; i < institutes.length; i++) {
                if(data['instituteid'] == institutes[i]['id']) {
                    $('.sl-institute').val(institutes[i]['institutename']);
                }
            }

            pro = data['profession'];
            professiontag = 1;
            getProfessions();
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