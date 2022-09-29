#!/usr/bin/env python
"""mapper.py"""

import sys

if __name__ == "__main__":
    count = 0
    sum_values = 0
    sum_squares = 0

    for row in sys.stdin:
        row = row.strip()
        row = row.split("\t", 1)
        try:
            temp_value = float(row[1])
        except (ValueError):
            continue
        count += 1
        sum_values += temp_value
        sum_squares += temp_value**2

    chunk_mean = sum_values / count
    chunk_var = (sum_squares / count) - chunk_mean**2
    print(count, chunk_mean, chunk_var, sep = "\t")

