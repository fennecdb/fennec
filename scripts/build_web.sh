#!/usr/bin/env bash
GOARCH=wasm GOOS=js go build -o ../resc/web/web.asm ../web/main.go
