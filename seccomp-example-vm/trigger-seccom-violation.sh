#!/bin/bash

set -x

firejail --noprofile --seccomp.drop=bind -c nc -v -l 8000

