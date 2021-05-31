#!/usr/bin/env sh

set -o errexit
set -o nounset

readonly cmd="$*"

database_ready () {
  # Check that minio is up and running on port `9000`:
  dockerize -wait 'tcp://db:5432' -timeout 5s
}

until database_ready; do
  >&2 echo 'Database is unavailable - sleeping'
done

>&2 echo 'Database is up - continuing...'

# Evaluating passed command (do not touch):
exec $cmd
