mkdir papka_1
cd papka_1
mkdir papka_2
cd papka_2
mkdir papka_3
cd..
echo %date% > star.txt
copy star.txt papka_2
cd papka_2
set data=%date:~0,2%.txt
rename star.txt %data%
set string=%data%
:round
if defined string (
	set /a amount+=1
	set string=%string:~0,-1%
	goto round
)
echo %amount% >> %data%
copy %data% papka_3
cd papka_3
for /f "tokens=4 delims=[] " %%i in ('ver') do (
	set version=%%i
)
echo %version% >> %data%



