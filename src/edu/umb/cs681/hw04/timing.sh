num_trials=$1
time=0
cd ~/cs/rust/concurrency681
while [ $num_trials -gt 0 ]
do
    time cargo run
    i=$[$i+1]
done
