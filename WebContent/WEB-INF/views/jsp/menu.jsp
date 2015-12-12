<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

   <div id="wrapper">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <img src="${pageContext.request.contextPath}/resources/img/ic_EHH_72_48.png" />
                </li>
                <li>
                   	<a id="userBtn" href="${pageContext.request.contextPath}/user">
					<spring:message code="label.mngUsers" /> 
				</a>
                </li>
                <li>
                    <a id="languageBtn" href="${pageContext.request.contextPath}/language">
					<spring:message code="label.mngLang" />
 				</a>
                </li>
                 <li>
                    <a href="logout">
					<spring:message code="label.logout" />
 				</a>
                </li>
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->
    </div>
    