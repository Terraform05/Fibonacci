fib() {
    x=0
    y=1
    for i in $(seq $1); do
        # echo -n "$x "
        sum=$((x + y))
        x=$y
        y=$sum
    done
    echo -n "$sum "
}
if [ -e error.txt ]; then rm error.txt; fi
for N in "$@"; do
    # echo "Printing Fibonacci Sequence to the '$N'th term:"
    case ${N} in
    *[!0-9]* | '') >&2 echo "$(date +%T): $N is not a positive integer, skipping $N" >>error.txt ;;
    *) fib $N ;;
    esac
done
echo

# ~/Argparse-ESD22F-PostAPCS-D-2$ bash main.sh 1 -5 3
# 1 -5 is not a positive integer, skipping -5
# 3
# ~/Argparse-ESD22F-PostAPCS-D-2$ bash main.sh 1 -5 3 2> error.txt
# 1 3
