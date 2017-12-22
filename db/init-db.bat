@echo off

echo.

echo.
pause
echo.

echo.

echo.
pause
echo.

echo.
pause
echo.

cd %~dp0
cd ..

call mvn antrun:run -Pinit-db

cd db
pause