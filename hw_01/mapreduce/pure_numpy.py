#!/usr/bin/env python3
"""numpy_mean_std.py"""

import csv
import numpy as np

PATH = "dataset.csv"
FEATURE = "price"

def get_mean_std(path = PATH, feature = FEATURE):
    
    with open(path, 'r', encoding="utf-8") as file:
        data = csv.DictReader(file, delimiter=',')
        arr = [float(row[feature]) for row in data if row[feature] is not None]
    
    print(np.mean(arr), np.var(arr), sep = "\t")


if __name__ == '__main__':
    get_mean_std()
