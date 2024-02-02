# conf.py

# Project information
project = 'HackingSim ReadTheDocs'
author = 'Tim'

# Extensions
extensions = [
    'sphinx.ext.autodoc',  # Automatically generate API documentation from docstrings
    'sphinx.ext.viewcode',  # Add "View source" links to your documentation
    'sphinx_rtd_theme',
]

html_theme = "sphinx_rtd_theme"
master_doc = 'index'
