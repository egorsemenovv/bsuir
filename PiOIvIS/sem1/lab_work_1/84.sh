mkdir papka_1
cd papka_1
mkdir papka_2
cd papka_2
mkdir papka_3
cd -
touch star.txt
date +"%d.%m.%Y" > star.txt
cp star.txt papka_2/
cd papka_2
mv star.txt $(date +"%d").txt
string="$(date +"%d").txt"
amount=${#string}
echo "$(date +"%d").txt" | awk '{print length}' >>  $(date +"%d").txt
cp $(date +"%d").txt papka_3/
cd papka_3
echo $(uname -s) | awk '{print substr($(uname -srm),12,10)}' >> $(date +"%d").txt