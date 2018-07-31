package com.megatronus.ui.common;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayList;
import java.util.List;


public class BaseAccessibilityService extends AccessibilityService
{

	@Override
	public void onAccessibilityEvent(AccessibilityEvent p1)
	{
		// TODO: Implement this method
	}

	@Override
	public void onInterrupt()
	{
		// TODO: Implement this method
	}

    public boolean AccessibilityIsEnabled(String serviceName)
	{
        List<AccessibilityServiceInfo> accessibilityServices =
		((AccessibilityManager) getApplicationContext().getSystemService(Context.ACCESSIBILITY_SERVICE))
		.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC);
        for (AccessibilityServiceInfo info : accessibilityServices)
		{
            if (info.getId().equals(serviceName))
			{
                return true;
            }
        }
        return false;
    }

    /**
     * 点击事件
     */
    public void ViewClick(AccessibilityNodeInfo nodeInfo)
	{
        if (nodeInfo == null)
		{
            return;
        }
        while (nodeInfo != null)
		{
            if (nodeInfo.isClickable())
			{
                nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                break;
            }
            nodeInfo = nodeInfo.getParent();
        }
    }

    /**
     * 返回操作
     */
    public void BackClick()
	{

        performGlobalAction(GLOBAL_ACTION_BACK);
    }


    /**
     * 模拟下滑操作
     */
    public void ScrollBackward()
	{

        performGlobalAction(AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD);
    }

    /**
     * 模拟上滑操作
     */
    public void ScrollForward()
	{

        performGlobalAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
    }


    public AccessibilityNodeInfo findViewByText(String text, boolean clickable)
	{
        List<AccessibilityNodeInfo> nodeInfoList = listFindViewByText(text, clickable);
        if (nodeInfoList != null && !nodeInfoList.isEmpty())
		{
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList)
			{
                if (nodeInfo != null && (nodeInfo.isClickable() == clickable))
				{
                    return nodeInfo;
                }
            }
        }
        return null;
    }

	public AccessibilityNodeInfo findViewByTextLast(String text, boolean clickable)
	{

        List<AccessibilityNodeInfo> nodeInfoList = listFindViewByText(text, clickable);
        if (nodeInfoList != null && !nodeInfoList.isEmpty())
		{
            for (int i = nodeInfoList.size() - 1 ; i >= 0 ; i--)
			{
				AccessibilityNodeInfo nodeInfo = nodeInfoList.get(i);
                if (nodeInfo != null && (nodeInfo.isClickable() == clickable))
				{
                    return nodeInfo;
                }
            }
        }
        return null;
    }

    public List<AccessibilityNodeInfo> listFindViewByText(String text, boolean clickable)
	{
        List<AccessibilityNodeInfo> accessibilityNodeInfoList = new ArrayList<>();

        AccessibilityNodeInfo accessibilityNodeInfo = getRootInActiveWindow();
        if (accessibilityNodeInfo == null)
		{
            return null;
        }
        List<AccessibilityNodeInfo> nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByText(text);
        if (nodeInfoList != null && !nodeInfoList.isEmpty())
		{
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList)
			{
                if (nodeInfo != null && (nodeInfo.isClickable() == clickable))
				{
                    accessibilityNodeInfoList.add(nodeInfo);
                }
            }
        }
        return accessibilityNodeInfoList;
    }

	public AccessibilityNodeInfo findViewById(String id)
	{

		List<AccessibilityNodeInfo> nodeInfoList = listFindViewById(id);
        if (nodeInfoList != null && !nodeInfoList.isEmpty())
		{
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList)
			{
                if (nodeInfo != null)
				{
                    return nodeInfo;
                }
            }
        }
        return null;
    }

	public List<AccessibilityNodeInfo> listFindViewById(String id)
	{
        AccessibilityNodeInfo accessibilityNodeInfo = getRootInActiveWindow();
        if (accessibilityNodeInfo == null)
		{
            return null;
        }
        List<AccessibilityNodeInfo> nodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(id);
        if (nodeInfoList != null && !nodeInfoList.isEmpty())
		{
            return nodeInfoList;
        }
        return null;
    }


    public void clickViewByText(String text)
	{
		List<AccessibilityNodeInfo> nodeInfoList = listFindViewByText(text, true);
        if (nodeInfoList != null && !nodeInfoList.isEmpty())
		{
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList)
			{
                if (nodeInfo != null)
				{
                    ViewClick(nodeInfo);
                    break;
                }
            }
        }
    }


	public void clickViewById(String id)
	{

        List<AccessibilityNodeInfo> nodeInfoList = listFindViewById(id);
        if (nodeInfoList != null && !nodeInfoList.isEmpty())
		{
            for (AccessibilityNodeInfo nodeInfo : nodeInfoList)
			{
                if (nodeInfo != null)
				{
                    ViewClick(nodeInfo);
                    break;
                }
            }
        }
    }


    public void inputText(AccessibilityNodeInfo nodeInfo, String text)
	{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
		{
            Bundle arguments = new Bundle();
            arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text);
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
        }
		else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
		{
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", text);
            clipboard.setPrimaryClip(clip);
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_PASTE);
        }
    }

}
