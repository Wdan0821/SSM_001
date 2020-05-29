<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2020/5/18
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        .content {
            width: 950px;
        }

        #All:hover {
            cursor: pointer;
        }

        .content table input {
            width: 15px;
            height: 15px;
        }

        .pagination li {
            list-style-type: none;
            float: left;
            padding: 10px;
        }
    </style>
    <script>
        // 获取你要查看的页码并且放到表单隐藏域里
        function subPage(page) {
            document.getElementById("pageNum").value = page;
            subForm();
        }

        // 提交表单
        function subForm() {
            document.getElementById("queryForm").setAttribute("action", "/customerController/findForSearch")
            document.getElementById("queryForm").submit();
        }

        function add() {
            window.location.href = "/customerController/toAdd"
        }

        function add1() {
            document.getElementById("addBtn1").setAttribute("action", "");
            document.getElementById("addBtn1").submit();
        }

        function qx() {
            var text = document.getElementById("All").innerHTML;
            if (text == '全选') {
                var a = document.getElementsByName("selectCustomerId");
                for (var i = 0; i < a.length; i++) {
                    a[i].checked = true;
                }
                document.getElementById("All").innerHTML = "取消";
            }else if (text == '取消') {
                var a = document.getElementsByName("selectCustomerId");
                for (var i = 0; i < a.length; i++) {
                    a[i].checked = false;
                }
                document.getElementById("All").innerHTML = "全选";
            }
        }
        function sc() {
            //获取选中的多选框
            var b=[];
            //获取所有的多选框
            var a=document.getElementsByName("selectCustomerId");
            alert(a.length);
            for (var i = 0; i < a.length; i++) {
                alert(a[i].checked);
                if (a[i].checked==true){
                    b.push(a[i]);
                }
            }
            if (b.length <= 0) {
                alert('请选择你想删除的数据');
            }else {
                if (confirm("你确定要删除吗")) {
                    //到controller删除的方法去
                    document.getElementById("queryForm").setAttribute("action","/customerController/doDelete");
                    document.getElementById("queryForm").submit();
                }
            }
        }
        function xg() {
            //获取选中的多选框的个数
            var count=0;
            //1.拿到所有的多选框
            var a=document.getElementsByName("selectCustomerId");
            for (var i = 0; i < a.length; i++) {
                if (a[i].checked == true) {
                    count++;
                }
            }
            //2.判断
            if (count>1){
                alert("只能选择一条数据进行修改");
            } else if (count < 1) {
                alert("请选择你要修改的数据");
            }else {
                document.getElementById("queryForm").setAttribute("action","/customerController/toUpdate");
                document.getElementById("queryForm").submit();            }
        }
    </script>
</head>
<body>
<h1>客户信息列表</h1>
<div class="content">
    <form:form modelAttribute="customer" id="queryForm">
        <%--传递页码的隐藏域--%>
        <input type="hidden" name="pageNum" id="pageNum">
        <div>
            <button type="button" id="queryBtn" onclick="subPage(1)">查询</button>
            <button type="button" id="addBtn" onclick="add1()">新增</button>
            <button type="button" id="updateBtn" onclick="xg()">修改</button>
            <button type="button" id="deleteBtn" onclick="sc()">删除</button>
        </div>
        <br/>
        <div>
            <span>客户编号:</span>
            <c:if test="${customer.customerId!=0}">
                <form:input path="customerId"/>
            </c:if>
            <c:if test="${customer.customerId==0}">
                <input type="text" id="customerId" name="customerId">
            </c:if>
            <span>客户名称:</span>
            <form:input path="customerName"/><br><br>
            <span>客户信息来源:</span>
            <form:select path="customerSourse">
                <form:option value="">请选择</form:option>
                <form:option value="A">电话营销</form:option>
                <form:option value="B">网络营销</form:option>
            </form:select>
            <span>创建日期:</span>
            <input type="text" id="customerDateBegin" name="customerDateBegin" value="${customerDateBegin}"/>
            <input type="text" id="customerDateEnd" name="customerDateEnd" value="${customerDateEnd}"/>
        </div>
        <br/>
        <table border="1px" cellpadding="5" cellspacing="0">
            <tr>
                <td width="5%" align="center" id="All" onclick="qx()">全选</td>
                <td width="15%" align="center">客户编号</td>
                <td width="15%" align="center">客户名称</td>
                <td width="15%" align="center">客户负责人</td>
                <td width="18%" align="center">客户信息来源</td>
                <td width="18%" align="center">客户所属行业</td>
                <td width="23%" align="center">创建日期</td>
            </tr>
            <c:forEach items="${list}" var="c">
                <tr>
                    <td width="15%" align="center"><input type="checkbox" name="selectCustomerId" value="${c.customerId}"></td>
                    <td width="15%" align="center">${c.customerId}</td>
                    <td width="15%" align="center">${c.customerName}</td>
                    <td width="15%" align="center">${c.customerUserName}</td>
                    <td width="18%" align="center">${c.customerSourseDict}</td>
                    <td width="18%" align="center">${c.customerIndustoryDict}</td>
                    <td width="23%" align="center">${c.customerDate}</td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <%--分页开始--%>
        <div>
            共有${totalRows}条数据,共${totalPage}页,当前为第${pageNum}页<br/>
            <ul class="pagination">
                    <%--上一页--%>
                <c:if test="${pageNum>1}">
                    <li><a href="javascript:void(0)" onclick="subPage(${pageNum-1})"><< </a></li>
                </c:if>
                <c:if test="${pageNum==1}">
                    <li><<</li>
                </c:if>

                    <%--中间页--%>
                <c:choose>
                    <%--总页数<=5--%>
                    <c:when test="${totalPage<=5}">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="${totalPage}"/>
                    </c:when>
                    <%--总页数>5--%>
                    <c:otherwise>
                        <c:set var="begin" value="${pageNum-1}"/>
                        <c:set var="end" value="${pageNum+3}"/>
                        <%--如果当前是第一页,最大页为5--%>
                        <c:if test="${begin-1<=0}">
                            <c:set var="begin" value="1"/>
                            <c:set var="end" value="5"/>
                        </c:if>
                        <%--end大于最大页,设置begin=最大值-4--%>
                        <c:if test="${end>totalPage}">
                            <c:set var="begin" value="${totalPage-4}"/>
                            <c:set var="end" value="${totalPage}"/>
                        </c:if>
                    </c:otherwise>
                </c:choose>

                    <%--遍历显示中间页码--%>
                <c:forEach var="i" begin="${begin}" end="${end}">
                    <%--当前页,选中--%>
                    <c:choose>
                        <c:when test="${i==pageNum}">
                            <li>${i}</li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="javascript:void(0)" onclick="subPage(${i})">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                    <%--下一页--%>
                <c:if test="${pageNum<totalPage}">
                    <li><a href="javascript:void(0)" onclick="subPage(${pageNum+1})"> >></a></li>
                </c:if>
                <c:if test="${pageNum==totalPage}">
                    <li>>></li>
                </c:if>
            </ul>
        </div>
        <%--分页结束--%>

    </form:form>
</div>

</body>
</html>
