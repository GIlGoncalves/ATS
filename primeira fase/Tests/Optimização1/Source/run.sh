#!/bin/bash
modprobe msr

java Main t1.txt < Tests/t1.txt > Outputs/t1Output.txt
java Main t2.txt < Tests/t2.txt > Outputs/t2Output.txt

linest1=$(diff Outputs/t1Output.txt TestsOutputs/t1Output.txt | wc -l)
linest2=$(diff Outputs/t2Output.txt TestsOutputs/t2Output.txt | wc -l)

echo "---------------------- TESTE 1 ----------------------"
if [ "$linest1" -eq 0 ]; then echo "T1.txt -> OK!"; else echo "T1.txt -> KO!"; fi;
cat EnergyTests/t1.txt

echo "---------------------- TESTE 2 ----------------------"
if [ "$linest2" -eq 0 ]; then echo "T2.txt -> OK!"; else echo "T2.txt -> KO!"; fi;
cat EnergyTests/t2.txt