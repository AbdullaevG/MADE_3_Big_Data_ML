#!/usr/bin/env python
"""reducer_mean.py"""

import sys


if __name__ == '__main__':
    size, mean = 0, 0
    for row in sys.stdin:
        row = row.strip()
        chunk_size, chunk_mean = map(float, row.split("\t", 1))

        mean += (chunk_size*chunk_mean + size*mean) / (size+chunk_size)
        size += chunk_size

    print(mean)
