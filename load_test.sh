#!/bin/bash

# Configuration
URL="http://localhost:8080/api/bookings/reserve"
EVENT_ID=1
TOTAL_USERS=500

echo "Starting load test for $TOTAL_USERS users..."

for i in $(seq 1 $TOTAL_USERS)
do
  # Randomly pick a seat between S1 and S5 to force competition
  SEAT_ID="S$(( ( RANDOM % 5 )  + 1 ))"
  USER_ID=$i

  # Run the curl command in the background (&)
  curl -s -X POST "$URL?eventId=$EVENT_ID&seatId=$SEAT_ID&userId=$USER_ID" -o /dev/null -w "User $USER_ID: %{http_code}\n" &

  # Pause every 50 requests to prevent Windows from crashing
  if (( $i % 50 == 0 )); then
    wait
    echo "Sent $i requests..."
  fi
done

wait
echo "Load test finished!"