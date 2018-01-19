@echo off
rem /**
rem  * Copyright &copy; 2017-2019 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
rem  *
rem  * Author: ThinkGem
rem  */
echo [INFO] ����Maven�汾��·��
echo.

cd %~dp0

set MAVEN_HOME=%cd%\maven3
set path=%MAVEN_HOME%\bin;%windir%\system32;%path%

echo [INFO] ��������...
echo.

setlocal enabledelayedexpansion
set txt=%cd%\repository

echo [INFO] �汾��·������Ϊ %txt%
echo.

set txt=!txt:\=/!
call :replace %MAVEN_HOME%\conf\settings.xml.default @REPO_DIR@ %txt% >%MAVEN_HOME%\conf\settings.xml

echo [INFO] ������������ɣ�����ذ�������˵�����в���������
echo.

echo [INFO] 1������ϵͳ��������, PATHǰ����ӣ�
echo [INFO]       %MAVEN_HOME%\bin;
echo.
echo [INFO] 2������ Eclipse ����
echo.
echo [INFO]    a���� Maven �����Window --^> Preferences --^> Maven 
echo [INFO]       --^> User Settings --^> ��� Browse... ��ť��ѡ����� 
echo [INFO]       %MAVEN_HOME%\conf\settings.xml �ļ���
echo.
echo [INFO]    b��û�� Maven �����Window --^> Preferences --^> Java 
echo [INFO]       --^> Bulid Path --^> Classpath Variables --^> ��� new... ��ť
echo [INFO]       Name��M2_REPO
echo [INFO]       Path��%cd%\repository
echo [INFO]       ��� OK ��ť
echo.

pause

goto :eof
:replace
for /f "tokens=1* delims=:" %%i in ('findstr /n ".*" %1') do (
	set txt=%%j
	if "!txt!" == "" (
		echo.
	) else (
		echo !txt:%2=%3!
	)
)
goto :eof