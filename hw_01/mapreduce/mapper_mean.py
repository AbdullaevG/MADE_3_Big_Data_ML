#!/usr/bin/env python
"""mapper.py"""

import sys

if __name__ == "__main__":
    count = 0
    sum_value = 0

    for row in sys.stdin:
        row = row.strip()
        row = row.split("\t", 1)
        try:
            temp_value = float(row[1])
        except (ValueError):
            continue
        count += 1
        sum_value += temp_value

    chunk_mean = sum_value / count
    print(count, chunk_mean, sep = "\t")
