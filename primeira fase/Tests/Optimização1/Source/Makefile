TARGET = libCPUScaler.so CPUScaler.o arch_spec.o msr.o dvfs.o
CFLAGS = -fPIC -g -c
JAVA_HOME = $(shell readlink -f /usr/bin/javac | sed "s:bin/javac::")
JAVA_INCLUDE = $(JAVA_HOME)/include
JAVA_INCLUDE_LINUX = $(JAVA_INCLUDE)/linux

all: compile lib_shared_CPUScaler 

compile: Main.java
	javac Main.java

lib_shared_CPUScaler:
	gcc $(CFLAGS) -I $(JAVA_INCLUDE) -I$(JAVA_INCLUDE_LINUX) CPUScaler.c arch_spec.c msr.c dvfs.c
	gcc -I $(JAVA_INCLUDE) -I $(JAVA_INCLUDE_LINUX) -shared -Wl,-soname,libCPUScaler.so -o libCPUScaler.so CPUScaler.o arch_spec.o msr.o dvfs.o -lc

clean:
	rm -f lib_share_CPUScaler $(TARGET)
	if [ -f Main.class ] ; then rm Main.class; fi;
	if [ -f Outputs/t1Output.txt ] ; then rm Outputs/t1Output.txt; fi;
	if [ -f Outputs/t2Output.txt ] ; then rm Outputs/t2Output.txt; fi;
	if [ -f caparicapost/User.class ] ; then rm caparicapost/*.class; fi; 
	if [ -f exceptions/ExceptionChronicleNotFound.class ] ; then rm exceptions/*.class; fi; 
	if [ -f EnergyTests/t1.txt ] ; then rm EnergyTests/t1.txt; fi; 
	if [ -f EnergyTests/t2.txt ] ; then rm EnergyTests/t2.txt; fi;
